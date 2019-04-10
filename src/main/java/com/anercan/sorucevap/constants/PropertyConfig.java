package com.anercan.sorucevap.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class PropertyConfig {


    @Value("${appconstant.question-status}")
    private int questionStatus;
}
