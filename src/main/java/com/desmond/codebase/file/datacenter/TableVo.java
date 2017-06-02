package com.desmond.codebase.file.datacenter;

/**
 * Created by Li.Xiaochuan on 16/5/7.
 */
public class TableVo {
    private String tableName;
    private String visitPage;
    private String vistPageDesc;

    public TableVo() {
    }

    public TableVo(String tableName, String visitPage, String vistPageDesc) {
        this.tableName = tableName;
        this.visitPage = visitPage;
        this.vistPageDesc = vistPageDesc;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getVisitPage() {
        return visitPage;
    }

    public void setVisitPage(String visitPage) {
        this.visitPage = visitPage;
    }

    public String getVistPageDesc() {
        return vistPageDesc;
    }

    public void setVistPageDesc(String vistPageDesc) {
        this.vistPageDesc = vistPageDesc;
    }
}
