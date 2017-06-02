package com.desmond.codebase.string;

/**
 * Created by Li.Xiaochuan on 15/10/28.
 */
public class SplitVO {
    private boolean isMatched;
    private String content;

    public SplitVO() {
    }

    public SplitVO(boolean isMatched, String content) {
        this.isMatched = isMatched;
        this.content = content;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setIsMatched(boolean isMatched) {
        this.isMatched = isMatched;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
