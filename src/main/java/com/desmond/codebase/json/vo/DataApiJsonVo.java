package com.desmond.codebase.json.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by Li.Xiaochuan on 16/3/13.
 */
public class DataApiJsonVo {
    private String method;

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("phone_uuid")
    private String phoneUuid;

    @JsonProperty("exectime")
    private Integer execTime;
    private String type;
    private String version;

    @JsonProperty("calltime")
    private Integer callTime;
    private Integer status;

    private Map<String, Object> input;
    private Object output;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhoneUuid() {
        return phoneUuid;
    }

    public void setPhoneUuid(String phoneUuid) {
        this.phoneUuid = phoneUuid;
    }

    public Integer getExecTime() {
        return execTime;
    }

    public void setExecTime(Integer execTime) {
        this.execTime = execTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getCallTime() {
        return callTime;
    }

    public void setCallTime(Integer callTime) {
        this.callTime = callTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<String, Object> getInput() {
        return input;
    }

    public void setInput(Map<String, Object> input) {
        this.input = input;
    }

    public Object getOutput() {
        return output;
    }

    public void setOutput(Object output) {
        this.output = output;
    }
}
