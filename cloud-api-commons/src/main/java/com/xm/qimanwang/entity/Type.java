package com.xm.qimanwang.entity;

public class Type {
    private Integer id;

    private String typeName;

    private String aliasa;

    private String aliasb;

    private String aliasc;

    private String publishdate;

    private String updatedate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getAliasa() {
        return aliasa;
    }

    public void setAliasa(String aliasa) {
        this.aliasa = aliasa == null ? null : aliasa.trim();
    }

    public String getAliasb() {
        return aliasb;
    }

    public void setAliasb(String aliasb) {
        this.aliasb = aliasb == null ? null : aliasb.trim();
    }

    public String getAliasc() {
        return aliasc;
    }

    public void setAliasc(String aliasc) {
        this.aliasc = aliasc == null ? null : aliasc.trim();
    }

    public String getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(String publishdate) {
        this.publishdate = publishdate == null ? null : publishdate.trim();
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate == null ? null : updatedate.trim();
    }
}