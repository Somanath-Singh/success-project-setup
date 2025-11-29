package com.aashdit.prod.heads.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class GetPropertyValue {

    private static Environment env;

    @Autowired
    public GetPropertyValue(Environment env) {
        GetPropertyValue.env = env;
    }

    public static String getPropertyValue(String key) {
        return env.getProperty(key);
    }
}
