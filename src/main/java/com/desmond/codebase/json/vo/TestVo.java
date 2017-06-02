package com.desmond.codebase.json.vo;

/**
 * Created by Li.Xiaochuan on 16/5/3.
 */
public class TestVo {
    private int id;
    private String title;

    private String testPro;

    public TestVo() {
    }

    public TestVo(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTestPro() {
        return testPro;
    }

    public void setTestPro(String testPro) {
        this.testPro = testPro;
    }
}
