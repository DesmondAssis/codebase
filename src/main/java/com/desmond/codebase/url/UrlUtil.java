package com.desmond.codebase.url;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Li.Xiaochuan on 15/10/19.
 */
public class UrlUtil {
    public static boolean isUrl(String imageSegment) {
        // the regex was from http://daringfireball.net/2010/07/improved_regex_for_matching_urls
        String regex = "(?i)\\b((?:[a-z][\\w-]+:(?:/{1,3}|[a-z0-9%])|www\\d{0,3}[.]|[a-z0-9.\\-]" +
                "+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’]))";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(imageSegment);

        return matcher.matches();
    }

    public static String getAppUrl(String action, Map<String, String> paramMap) {
        String url = "";
        if ("webview".equals(action)){
            if (StringUtils.isNotBlank(paramMap.get("url"))) {
                url = StringUtils.replace(paramMap.get("url"), "http://", "inapp://");
                url = StringUtils.replace(url, "https://", "inapp://");
            }

        } else if("browser".equals(action)) {
            if (StringUtils.isNotBlank(paramMap.get("url"))) {
                url = paramMap.get("url");
            }

        } else if("".equals(action)) {
            // do nothing.
        } else {
            if(MapUtils.isNotEmpty(paramMap)) {
                StringBuilder sbUrl = new StringBuilder();

                int count = 1;
                for(Map.Entry<String, String> entry : paramMap.entrySet()){
                    String value = entry.getValue();
                    try {
                        value = URLEncoder.encode(value, "UTF-8");
                    } catch (Exception e) {
//                        LOG.error("对URL编码出错：" + sbUrl.toString() + e.getMessage(), e);
                    }

                    sbUrl.append(entry.getKey()).append("=").append(value).append(count < paramMap.size() ? "&" : "");

                    count++;
                }

                url = "inapp://" + action + "/?" + sbUrl.toString();

            } else {
                url = "inapp://" + action;
            }
        }

        return url;
    }

    /**
     * 反向解析appUrl.
     * inapp://actlist/?tags=320&sstime=&setime=&estime=&eetime=&exclude_activity=&sort=id&free=0&same_city=2
     * @param url
     * @return map1: key:action, value: map2<br/>
     *         map2: key: paramKey, value: paramValue
     */
    public static Map<String, Map<String, String>> parseAppUrl(String url) {
        Map<String, Map<String, String>> resultMap = new HashMap<>();
        if(StringUtils.isNotBlank(url)) {
            String prefix = "inapp://";
            if(url.indexOf("http://") == 0 || url.indexOf("https://") == 0) {
                Map<String, String> valueMap = new HashMap<>();
                valueMap.put("url", url);
                resultMap.put("browser", valueMap);

                return resultMap;
            } else if(!url.equals(prefix) && url.indexOf(prefix) == 0) {
                url = url.replaceAll(prefix, "");
                String[] tmp = url.split("/");
                if(tmp[0] != null && tmp[0].indexOf(".") > 0) {
                    Map<String, String> valueMap = new HashMap<>();
                    valueMap.put("url", "http://" + url);
                    resultMap.put("webview", valueMap);

                    return resultMap;
                } else {
                    if(tmp.length > 1) {
                        StringBuilder trimedUrlSb = new StringBuilder();
                        for(int i = 0; i < tmp.length; i++) {
                            if(i > 0) {
                                trimedUrlSb.append(tmp[i]);
                                if(i < tmp.length - 1) {
                                    trimedUrlSb.append("/");
                                }
                            }
                        }
                        url = trimedUrlSb.toString();
                        if(url != null && url.startsWith("?")) {
                            url = url.substring(1); // 去掉'?'.
                        }
                        if(url != null) {
                            String[] paramArr = url.split("&");
                            Map<String, String> valueMap = new HashMap<>();
                            resultMap.put(tmp[0], valueMap);
                            for(String params : paramArr) {
                                String[] pArr = params.split("=");
                                if(pArr.length == 2) {
                                    valueMap.put(pArr[0], pArr[1]);
                                } else if(pArr.length == 1) {
                                    valueMap.put(pArr[0], "");
                                }
                            }
                            return resultMap;
                        }
                    }
                }
            }
        }

        return resultMap;
    }

    public static void main(String[] args) {
        String s = UrlUtil.encodeUrl("2016-08-25 20:26:31");
        System.out.println(s.replace("+","%20"));
        System.out.println(UrlUtil.decodeUrl(s));
    }

    public static void printt(String url) {
        Map<String, Map<String, String>> urlMap = parseAppUrl(url);

        String action = "";
        // 获取 action,只有一个.
        if(!urlMap.isEmpty()) {
            Iterator<String> iterator = urlMap.keySet().iterator();
            while (iterator.hasNext()) {
                action = iterator.next();
            }
        }
        System.out.println(action);
    }

    /**
     * 对url 采取编码(UTF-8).
     * @param url
     * @return
     */
    public static String encodeUrl(String url) {
        if(StringUtils.isNotBlank(url)) {
            try {
                return URLEncoder.encode(url, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return url;
    }

    /**
     * 对url 采取编码(按指定编码型号).
     * @param url
     * @return
     */
    public static String encodeUrl(String url, String charSet) {
        if(StringUtils.isNotBlank(url)) {
            try {
                return URLEncoder.encode(url, charSet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return url;
    }

    public static String decodeUrl(String url) {
        return decodeUrl(url, "utf-8");
    }

    /**
     * 对url 采取解码.
     * @param url
     * @return
     */
    public static String decodeUrl(String url, String charset) {
        if(StringUtils.isNotBlank(url)) {
            try {
                return URLDecoder.decode(url, charset);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return url;
    }


    public static Map<String, String> parseUrl(String url) {
        Map<String, String> resultMap = new HashMap<>();
        if(com.desmond.codebase.string.StringUtils.isNotBlank(url)) {
            String parmUrl = url.substring(url.indexOf("?") + 1);
            String[] parmArr = parmUrl.split("&");
            for(String p : parmArr) {
                String[] t = p.split("=");
                resultMap.put(t[0], t[1]);
            }
        }

        return resultMap;
    }
}
