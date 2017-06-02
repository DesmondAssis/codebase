package com.desmond.codebase.network.vo;

import java.io.Serializable;

/**
 * Created by Li.Xiaochuan on 16/12/24.
 */
public class HpCohortVo implements Serializable{
    private int date;
    private int phoneId;
    private int cityId;
    private String channel = "";

    public HpCohortVo() {
    }

    public HpCohortVo(int date, int phoneId, int cityId, String channel) {
        this.date = date;
        this.phoneId = phoneId;
        this.cityId = cityId;
        this.channel = channel;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
