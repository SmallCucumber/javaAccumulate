package com.zmm.config;

import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.RandomUtil;
import io.opentracing.ActiveSpan;
import io.opentracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.apache.skywalking.apm.toolkit.opentracing.SkywalkingTracer;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Tracer tracer = new SkywalkingTracer();

    /**
     * skywalking自定义tag的key，记录错误信息
     */
    public static final String SKYWALKING_TAG_ERR_MSG_KEY = "err.msg";

    /**
     * skywalking自定义tag的key，记录错误code
     */
    public static final String SKYWALKING_TAG_ERR_CODE_KEY = "err.code";


    @ExceptionHandler(Exception.class)
    public Map handleException(Exception e) {
        String skywalkingCode = getRandomCode();
        log.error(e.getMessage(), e);
        setSkywalkingTagErrMsg("系统异常", skywalkingCode);

        Map returnMap=new HashMap();
        returnMap.put("code","500");
        returnMap.put("msg",getRandomCodeMsg(skywalkingCode, "系统异常"));
        return returnMap;
    }

    /**
     * 添加skywalking的自定义tag-err
     *
     * @param msg
     * @param code
     */
    private void setSkywalkingTagErrMsg(String msg, String code) {
        ActiveSpan span = tracer.activeSpan();
        span.setTag(SKYWALKING_TAG_ERR_MSG_KEY, msg);
        span.setTag(SKYWALKING_TAG_ERR_CODE_KEY, code);
    }

    /**
     * 获取随机数字code
     *
     * @return java.lang.String
     */
    public String getRandomCode() {
        return RandomUtil.randomNumbers(8);
    }

    /**
     * 组装msg
     *
     * @param randomCode 随机数字code
     * @param msg        msg
     * @return java.lang.String
     * @throws
     */
    private String getRandomCodeMsg(String randomCode, String msg) {
        return StrFormatter.format("【{}】{}", randomCode, msg);
    }
}
