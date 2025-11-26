package com.sana.base.syshandle.conbination;

import com.sana.base.syshandle.exception.SanaExceptionCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author LON
 * @create 2025/7/12
 */
@Data
@Schema(description = "响应")
public class ConbinationResult<T> {
    @Schema(description = "编码 200表示成功，其他值表示失败")
    private int code = 200;

    private int count = 0;

    @Schema(description = "消息内容")
    private String msg = "success";

    //@JsonSerialize(using = LongToStringSerializer.class)
    //@JsonDeserialize(using = StringToLongDeserializer.class)
    @Schema(description = "响应数据")
    private T data;

    public static <T> ConbinationResult<T> ok() {

        return ok(null);
    }

    public static <T> ConbinationResult<T> ok(T data) {
        ConbinationResult<T> sanaResult = new ConbinationResult<>();
        sanaResult.setData(data);
        return sanaResult;
    }

    public static <T> ConbinationResult<T> ok(int count,T data) {
        ConbinationResult<T> sanaResult = new ConbinationResult<>();
        sanaResult.setCount( count);
        sanaResult.setData(data);
        return sanaResult;
    }

    public static <T> ConbinationResult<T> error() {

        return error(SanaExceptionCode.INTERNAL_SERVER_ERROR);
    }

    public static <T> ConbinationResult<T> error(String msg) {
        return error(SanaExceptionCode.INTERNAL_SERVER_ERROR.getCode(), msg);
    }

    public static <T> ConbinationResult<T> error(SanaExceptionCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static <T> ConbinationResult<T> error(int code, String msg) {
        ConbinationResult<T> sanaResult = new ConbinationResult<>();
        sanaResult.setCode(code);
        sanaResult.setMsg(msg);
        return sanaResult;
    }
}
