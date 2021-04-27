package com.xm.qimanwang.entity;

public class Resource {
    private Long id;

    private String resName;

    private Integer type;

    private String resUrl1;

    private String resUrl2;

    private String resUrl3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName == null ? null : resName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getResUrl1() {
        return resUrl1;
    }

    public void setResUrl1(String resUrl1) {
        this.resUrl1 = resUrl1 == null ? null : resUrl1.trim();
    }

    public String getResUrl2() {
        return resUrl2;
    }

    public void setResUrl2(String resUrl2) {
        this.resUrl2 = resUrl2 == null ? null : resUrl2.trim();
    }

    public String getResUrl3() {
        return resUrl3;
    }

    public void setResUrl3(String resUrl3) {
        this.resUrl3 = resUrl3 == null ? null : resUrl3.trim();
    }
}