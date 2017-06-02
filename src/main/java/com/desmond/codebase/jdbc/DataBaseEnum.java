package com.desmond.codebase.jdbc;

/**
 * Created by Li.Xiaochuan on 16/12/15.
 */
public enum DataBaseEnum {
    WANZHOUMO("wanzhoumo"),
    DATA_CENER("data_center"),
    TEST("test");

    DataBaseEnum(String dbName) {
        this.dbName = dbName;
    }

    private String dbName;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
