package com.desmond.codebase.bean;

/**
 * Created by Li.Xiaochuan on 16/3/16.
 */
public class TargetBean {
    private Long id;

    private Integer phoneId;

    private Boolean isTrue;

    private String visitPage;

    private Short type;

    private Long testTargetLog;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }

    public Boolean getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(Boolean isTrue) {
        this.isTrue = isTrue;
    }

    public String getVisitPage() {
        return visitPage;
    }

    public void setVisitPage(String visitPage) {
        this.visitPage = visitPage;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Long getTestTargetLog() {
        return testTargetLog;
    }

    public void setTestTargetLog(Long testTargetLog) {
        this.testTargetLog = testTargetLog;
    }
}
