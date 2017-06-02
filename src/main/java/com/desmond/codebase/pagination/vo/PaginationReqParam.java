package com.desmond.codebase.pagination.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Li.Xiaochuan on 16/3/22.
 */
public class PaginationReqParam {
    private int offset;

    @JsonProperty("pagesize")
    private int pageSize;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
