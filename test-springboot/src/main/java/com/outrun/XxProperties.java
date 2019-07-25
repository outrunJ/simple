package com.outrun;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by outrun on 7/24/19.
 */
@Component
@ConfigurationProperties(prefix = "xx")
public class XxProperties {
    private Integer yy;

    public void setYy(Integer yy) {
        this.yy = yy;
    }

    public Integer getYy() {
        return yy;
    }
}
