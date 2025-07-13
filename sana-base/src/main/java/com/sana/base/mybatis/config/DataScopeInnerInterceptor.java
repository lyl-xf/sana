package com.sana.base.mybatis.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.validation.ValidationException;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author LON
 * @version 1.0
 * @data 2025/7/13 17:57
 */
public class DataScopeInnerInterceptor implements InnerInterceptor {

    /**
     * 在查询之前执行操作，根据数据权限范围动态修改SQL。
     *
     * @param executor 执行器，用于执行SQL。
     * @param ms 映射语句，描述了如何从数据库中获取数据。
     * @param parameter 查询参数。
     * @param rowBounds 行限制，用于指定查询的起始行和行数。
     * @param resultHandler 结果处理器，用于处理查询结果。
     * @param boundSql 原始SQL对象，包含查询SQL和参数信息。
     */
    private static final Logger log = LoggerFactory.getLogger(DataScopeInnerInterceptor.class);

    // 允许使用的字段白名单
    private static final Set<String> ALLOWED_COLUMNS = Set.of("creator", "org_id");

    @Override
    public void beforeQuery(Executor executor, MappedStatement ms, Object parameter, RowBounds rowBounds,
                            ResultHandler resultHandler, BoundSql boundSql) {
        if (!(parameter instanceof Map)) {
            return;
        }
        Map<?, ?> paramMap = (Map<?, ?>) parameter;

        Boolean dataScopeIgnore = getBooleanParam(paramMap, "DataScopeIgnore");
        if (dataScopeIgnore == null || !dataScopeIgnore) {
            return;
        }

        String scope = getDataScope(paramMap);
        if (scope == null || StrUtil.isBlank(scope)) {
            return;
        }

        try {
            String buildSql = getSelectSql(boundSql.getSql(), scope);
            PluginUtils.mpBoundSql(boundSql).sql(buildSql);
        } catch (Exception e) {
            log.warn("Failed to modify SQL for data scope filtering.", e);
        }
    }

    /**
     * 获取布尔类型的参数值
     */
    private Boolean getBooleanParam(Map<?, ?> paramMap, String key) {
        Object value = paramMap.get(key);
        if (value instanceof Boolean) {
            return (Boolean) value;
        } else if (value instanceof String) {
            return Boolean.parseBoolean((String) value);
        }
        return null;
    }

    /**
     * 根据用户权限构造 WHERE 条件字符串
     */
    private String getDataScope(Map<?, ?> parameter) {
        List<Long> dataScopeList = getCurrentUserDataScope(); // 示例方法，需替换为真实获取逻辑
        if (dataScopeList == null || dataScopeList.isEmpty()) {
            return null;
        }

        return "creator in (" + StrUtil.join(",", dataScopeList) + ")";
    }

    /**
     * 从用户上下文中获取权限范围（示例实现）
     */
    private List<Long> getCurrentUserDataScope() {
        // 示例：从 ThreadLocal 或 SecurityContext 获取当前用户信息
        // User user = UserContext.getCurrentUser();
        // return user != null ? user.getDataScope() : null;
        return Arrays.asList(1L, 2L, 3L); // 仅用于演示
    }

    /**
     * 修改原始 SQL，添加数据权限 WHERE 条件
     */
    private String getSelectSql(String originalSql, String scope) throws JSQLParserException {
        Select select = (Select) CCJSqlParserUtil.parse(originalSql);
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();

        String tableName = getTableName(plainSelect);
        Expression newCondition = parseCondition(tableName, scope);

        appendConditionToSql(plainSelect, newCondition);

        return select.toString();
    }

    /**
     * 解析字符串形式的 WHERE 条件为 Expression
     */
    private Expression parseCondition(String tableName, String condition) throws JSQLParserException {
        // 白名单校验字段名
        String[] parts = condition.trim().split("\\s+");
        if (parts.length < 3 || !ALLOWED_COLUMNS.contains(parts[0])) {
            throw new IllegalArgumentException("Invalid column name in data scope: " + parts[0]);
        }

        return CCJSqlParserUtil.parseCondExpression(condition);
    }

    /**
     * 添加 WHERE 条件到 SQL 中
     */
    private void appendConditionToSql(PlainSelect plainSelect, Expression newCondition) {
        Expression existingWhere = plainSelect.getWhere();
        if (existingWhere == null) {
            plainSelect.setWhere(newCondition);
        } else {
            plainSelect.setWhere(new AndExpression(existingWhere, newCondition));
        }
    }

    /**
     * 获取 FROM 子句中的表名或别名
     */
    public String getTableName(PlainSelect plainSelect) {
        try {
            Table table = (Table) plainSelect.getFromItem();
            Alias alias = table.getAlias();
            return alias != null ? alias.getName() : table.getName();
        } catch (ClassCastException e) {
            log.warn("Unsupported FROM item type: {}", plainSelect.getFromItem().getClass());
            return "";
        }
    }
}
