package com.aashdit.setup.utils;

public class MainAppConstants {



    public static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/webjars/**",
            "/actuator/health",
            "/swagger-ui/**",
            //"/erp",
            // other public endpoints of your API may be appended to this array
            "/api/login",
            "/api/allow",
            "/api/allowAll",
            "/api/core",
            "/allow/get-role-by-roleCode",
            "/public/**",
            "/api/public/check-app-version",
            "/stageConfig/public/getStageButtonsOnFormOnFlowCode",
            "/public/lodgeGrievance",
            "/public/",
            "/api/save-public",
            "/api/login",
            "/common/api"
    };
    public static boolean isSkipUrl(String url) {
        for (String skipUrl : AUTH_WHITELIST) {
            if (url.contains(skipUrl)) {
                return true;
            }
        }
        return false;
    }

    public static final String VENDOR_USER_NAME = "admin";


}
