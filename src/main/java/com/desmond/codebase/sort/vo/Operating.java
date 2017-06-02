package com.desmond.codebase.sort.vo;

/**
 * Operating is a Querydsl bean type
 */
public class Operating {
    private String title;

    private String poiId;

    private String channel;

    private Integer sort;

    private Integer createTime;

    public Operating(String title, String poiId, String channel, Integer sort, Integer createTime) {
        this.title = title;
        this.poiId = poiId;
        this.channel = channel;
        this.sort = sort;
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Operating{" +
                "title='" + title + '\'' +
                ", poiId='" + poiId + '\'' +
                ", channel='" + channel + '\'' +
                ", sort=" + sort +
                ", createTime=" + createTime +
                '}';
    }
}

