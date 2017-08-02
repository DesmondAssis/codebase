package com.desmond.codebase.jdbc.vo;

import java.util.List;

/**
 * Created by presleyli on 2017/6/27.
 */
public class ExpressCompanyVo {

    private String status;
    private String msg;
    private List<ExpressCompanyItem> result;

    public ExpressCompanyVo() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ExpressCompanyItem> getResult() {
        return result;
    }

    public void setResult(List<ExpressCompanyItem> result) {
        this.result = result;
    }
}
