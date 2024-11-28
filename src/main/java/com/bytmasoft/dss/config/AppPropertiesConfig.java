package com.bytmasoft.dss.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.properties")
public class AppPropertiesConfig {

private initData initData;
private DateFormat dateFormat;


@Getter
@Setter
public static class initData {
    private boolean initialize;
}

@Getter
@Setter
public static class DateFormat{
    private String yearFirst;
    private String dayFirst;
}



}
