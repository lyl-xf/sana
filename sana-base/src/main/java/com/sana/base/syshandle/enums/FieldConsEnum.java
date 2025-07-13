package com.sana.base.syshandle.enums;

/**
 * @author LON
 * @version 1.0
 * @data 2025/7/13 18:02
 */
public enum FieldConsEnum {
    CREATE_TIME("createTime"),
    CREATOR("creator"),
    UPDATE_TIME("updateTime"),
    UPDATER("updater"),
    DELETED("deleted"),
    CREATOR_NAME("creatorName"),
    UPDATER_NAME("updaterName");

    private final String fieldName;

    FieldConsEnum(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
