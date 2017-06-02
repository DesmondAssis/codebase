package com.desmond.codebase.http.wzm;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseApi {
    private String status;
    private Object result;

    @JsonProperty(value = "server_time")
    private int serverTime;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public int getServerTime() {
        return serverTime;
    }

    public void setServerTime(int serverTime) {
        this.serverTime = serverTime;
    }
}
