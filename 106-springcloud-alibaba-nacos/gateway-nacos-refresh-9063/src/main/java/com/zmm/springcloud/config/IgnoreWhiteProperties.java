package com.zmm.springcloud.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
@ConfigurationProperties(prefix = "ignore")
@Data
public class IgnoreWhiteProperties {

    /**
     * 放行白名单配置，网关不校验此处的白名单
     */
    private String[] whites;

    public String[] getWhites() {
        return whites;
    }

    public void setWhites(String[] whites) {
        this.whites = whites;
    }
}
