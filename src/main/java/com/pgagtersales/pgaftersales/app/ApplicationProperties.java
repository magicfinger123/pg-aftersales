package com.pgagtersales.pgaftersales.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {
    @Autowired
    private Environment env;

    public String getTokenSecret(){
       // return env.getProperty("token_secret");
        return "hudqwkjdndbewhk";
    }
}
