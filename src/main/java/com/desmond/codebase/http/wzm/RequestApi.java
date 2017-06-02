package com.desmond.codebase.http.wzm;

import com.desmond.codebase.url.UrlUtil;

import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class RequestApi {
    private HttpClientApi client;

    private TreeMap<String, String> queryMap;

    public RequestApi() {
        client = new HttpClientApi();

        queryMap = new TreeMap<>();
//        queryMap.put(ConfigApi.VERSION_KEY, ConfigApi.VERSION);
//        queryMap.put(ConfigApi.TIMESTAMP_KEY, getNow());

        initDevice();
    }

    /**
     * 初始化用户访问设备信息
     */
    private void initDevice() {
        setHeader(ConfigApi.APPVCODE_KEY, "34");
        setHeader(ConfigApi.APPVNAME_KEY, "4.2.0");
        setHeader(ConfigApi.CHANNEL_KEY, "and-a1");
        setHeader(ConfigApi.OS_KEY, "android");
        setHeader(ConfigApi.APP_KEY_KEY, ConfigApi.APP_KEY);
        setHeader(ConfigApi.CONTENT_TYPE, ConfigApi.CONTENT_TYPE_JSON);

        if (ConfigApi.TOP_SESSION != null && !ConfigApi.TOP_SESSION.isEmpty()) {
            setHeader(ConfigApi.SESSION_KEY, ConfigApi.TOP_SESSION);
        }

//        queryMap.put(ConfigApi.UUID_KEY, "ffffffff-de70-ca7f-2911-94bb0033c587");
    }

    public void setHeader(String k, String v) {
        client.setHeader(k, v);
    }

    //http://test5.wanzhoumo.com:8080/activity/homelist?UUID=ffffffff-de70-ca7f-2911-94bb0033c587&fields_version=3.3&keyword=东海&lat=31.224868&lon=121.448383&offset=0&pagesize=100&timestamp=1448345077&v=3.0
    public ResponseApi get(String url) {
        if(UrlUtil.isUrl(url)) {
            url = url.substring(7);
            String params = url.substring(url.indexOf("?") + 1);
            String path = url.substring(0, url.indexOf("?"));
            int firstP = url.indexOf("/");
            String api = url.substring(0, firstP);
            String command = path.substring(firstP + 1);
            TreeMap<String, String> treeMap = new TreeMap<>();
            String[] paramArr = params.split("&");
            for (String str : paramArr) {
                String[] p = str.split("=");
                treeMap.put(p[0], p[1]);
            }
            api = "http://" + api;

            return GET(command, api, treeMap);
        }

        return null;
    }

    public ResponseApi GET(String command, String api, TreeMap<String, String> query) {
        client.setMethod("GET");

        if (query != null) {
            queryMap.putAll(query);
        }

        return call(command, api);
    }

    public ResponseApi POST(String command, TreeMap<String, Object> data) {
        client.setMethod("POST");
        client.setPostData(data);

        return call(command, null);
    }

    public ResponseApi PUT(String command, TreeMap<String, Object> data) {
        client.setMethod("PUT");
        client.setPostData(data);

        return call(command, null);
    }

    public ResponseApi DELETE(String command, TreeMap<String, Object> data) {
        client.setMethod("DELETE");
        client.setPostData(data);

        return call(command, null);
    }


    private ResponseApi call(String command, String api) {
        String url = (api != null ? api : ConfigApi.API_URL) + "/" + command;
        if (queryMap.size() > 0) {
            url += "?" + buildQueryString(queryMap, true);
        }

        String sign = buildSign(queryMap);
        setHeader(ConfigApi.APP_SIGN_KEY, sign);

        return client.call(url);
    }

    private String buildQueryString(Map<String, String> args, boolean urlEncode) {
        Set<Map.Entry<String, String>> entries = args.entrySet();
        Iterator<Map.Entry<String, String>> it = entries.iterator();
        StringBuilder builder = new StringBuilder();

        try {
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();

                String key = entry.getKey();
                String value = entry.getValue();

                if (key == null || key.isEmpty())
                    continue;
                if (value == null || value.isEmpty()) {
                    if (!urlEncode)
                        continue;
                }
                if (urlEncode) {
                    key = URLEncoder.encode(key, "UTF-8");
                    value = URLEncoder.encode(value, "UTF-8");
                }

                builder.append(key + "=" + value);

                if (it.hasNext()) {
                    builder.append("&");
                }
            }
        } catch (Exception e) {
            return "";
        }
        return builder.toString();
    }

    private String getNow() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return "" + (timestamp.getTime() / 1000);
    }

    private String buildSign(Map<String, String> params) {
        params.put(ConfigApi.APP_KEY_FOR_SIGN, ConfigApi.APP_KEY);
        String signString = String.format("%s&%s", buildQueryString(params, false), ConfigApi.APP_SECRET);
        return Md5Api.calculate(signString);
    }
}
