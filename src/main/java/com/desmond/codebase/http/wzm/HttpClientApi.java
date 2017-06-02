package com.desmond.codebase.http.wzm;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.util.HashMap;
import java.util.TreeMap;

public class HttpClientApi {
    private String contentType = "application/json";
    private String charset = "UTF-8";

    private String url;
    private HashMap<String, String> headers;
    private String method = "GET";
    private int timeout = 3000;
    private String postString = "";


    public HttpClientApi() {
        headers = new HashMap<>();
        headers.put("Content-Type", contentType);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHeader(String k, String v) {
        headers.put(k, v);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setPostData(TreeMap<String, Object> postData) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            postString = objectMapper.writeValueAsString(postData);
        }catch (Exception e) {
        }
    }

    public ResponseApi call(String url) {
        HttpClient httpClient=new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);

        HttpMethodBase request = null;
        ResponseApi responseApi = new ResponseApi();
        try {
            if (method.toUpperCase() == "GET") {
                GetMethod getRequest = new GetMethod(url);
                request = getRequest;
            } else {
                PostMethod postRequest = new PostMethod(url);
                postRequest.setRequestEntity(new StringRequestEntity(postString, contentType, charset));
                postRequest.setRequestHeader("X-HTTP-Method-Override", method.toUpperCase());

                request = postRequest;
            }

            if (headers != null && headers.size() > 0) {
                for (String k : headers.keySet()) {
                    request.setRequestHeader(k, headers.get(k));
                }
            }

            httpClient.executeMethod(request);
            if (request.getStatusCode() != 200) {
                throw new Exception("服务器开小差了");
            }

            String response = request.getResponseBodyAsString();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(response, ResponseApi.class);
        } catch (Exception e) {
            responseApi.setStatus("error");
            responseApi.setResult("服务器开小差了");

            return responseApi;
        } finally {
            if(request != null){
                request.releaseConnection();//释放链接
            }
        }
    }


}
