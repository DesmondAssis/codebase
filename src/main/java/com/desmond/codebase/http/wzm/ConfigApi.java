package com.desmond.codebase.http.wzm;

public class ConfigApi {
    public static boolean isProd = true;
    public static final String API_URL = "https://api-test5.wanzhoumo.com";
    public static final String APP_KEY = "800000002";
    public static final String APP_SECRET = isProd ? "c85e4c825d61b4328eb0aae659e4a177" :"123456";

    public static final String VERSION_KEY = "v";
    public static final String VERSION = "3.0";

    public static final String UUID_KEY = "UUID";
    public static final String APPVCODE_KEY = "APP_V_CODE";
    public static final String APPVNAME_KEY = "APP_V_NAME";
    public static final String CHANNEL_KEY = "CHANNEL";
    public static final String OS_KEY = "OS";

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_TYPE_JSON = "application/json;charset=utf-8";

    public static final String SESSION_KEY = "TOP_SESSION";
    public static final String TIMESTAMP_KEY = "timestamp";
    public static final String APP_KEY_FOR_SIGN = "app_key";
    public static final String APP_KEY_KEY = "HTTP_X_REST_APPKEY";
    public static final String APP_SIGN_KEY = "HTTP_X_REST_APPSIGN";

    public static String TOP_SESSION = "ucob70rpo1lh36jddlnakr0805";
}
