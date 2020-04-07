package com.pgagtersales.pgaftersales.security;


import com.pgagtersales.pgaftersales.SpringApplicationContext;
import com.pgagtersales.pgaftersales.app.ApplicationProperties;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 864000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/user";
    //public static final String TOKEN_SECRET = "uT_9uh0whr42o1hb";

    public static String getTokenSecret()
    {
        ApplicationProperties applicationProperties = (ApplicationProperties) SpringApplicationContext.getBean("ApplicationProperties");
        return applicationProperties.getTokenSecret();
    }
}
