package com.sana.base.syshandle.result;

import com.sana.base.syshandle.exception.SanaExceptionCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author LON
 * @create 2025/7/12
 */
@Data
@Schema(description = "响应")
public class SanaResult<T> {
    @Schema(description = "编码 200表示成功，其他值表示失败")
    private int code = 200;

    @Schema(description = "消息内容")
    private String msg = "success";

    //@JsonSerialize(using = LongToStringSerializer.class)
    //@JsonDeserialize(using = StringToLongDeserializer.class)
    @Schema(description = "响应数据")
    private T data;

    public static <T> SanaResult<T> ok() {

        return ok(null);
    }

    public static <T> SanaResult<T> ok(T data) {
        SanaResult<T> lanaResult = new SanaResult<>();
        lanaResult.setData(data);
        return lanaResult;
    }

    public static <T> SanaResult<T> error() {

        return error(SanaExceptionCode.INTERNAL_SERVER_ERROR);
    }

    public static <T> SanaResult<T> error(String msg) {
        return error(SanaExceptionCode.INTERNAL_SERVER_ERROR.getCode(), msg);
    }

    public static <T> SanaResult<T> error(SanaExceptionCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static <T> SanaResult<T> error(int code, String msg) {
        SanaResult<T> lanaResult = new SanaResult<>();
        lanaResult.setCode(code);
        lanaResult.setMsg(msg);
        return lanaResult;
    }
}
