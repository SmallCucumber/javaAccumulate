package com.zmm.springcloud.constant;

import java.io.Serializable;

/**
 * @program: javaAccumulate
 * @description:
 * @author: ZhaoManMan
 * @create: 2022-03-10 10:31
 **/
public class ResponseData <T> implements Serializable {

    private static final long serialVersionUID = 5383507942557166124L;

    /** 成功 */
    public static final int SUCCESS = CommonConst.SUCCESS;

    /** 失败 */
    public static final int FAIL = CommonConst.FAIL;

    private int code;

    private String msg;

    private T data;

    public static <T> ResponseData<T> ok()
    {
        return restResult(null, SUCCESS, null);
    }

    public static <T> ResponseData<T> ok(T data)
    {
        return restResult(data, SUCCESS, null);
    }

    public static <T> ResponseData<T> ok(T data, String msg)
    {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> ResponseData<T> fail()
    {
        return restResult(null, FAIL, null);
    }

    public static <T> ResponseData<T> fail(String msg)
    {
        return restResult(null, FAIL, msg);
    }

    public static <T> ResponseData<T> fail(T data)
    {
        return restResult(data, FAIL, null);
    }

    public static <T> ResponseData<T> fail(T data, String msg)
    {
        return restResult(data, FAIL, msg);
    }

    public static <T> ResponseData<T> fail(int code, String msg)
    {
        return restResult(null, code, msg);
    }

    public static <T> ResponseData<T> restResult(T data, int code, String msg)
    {
        ResponseData<T> apiResult = new ResponseData<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

}
