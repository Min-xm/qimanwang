package com.xm.qimanwang.entity;

public class Like {
    private Integer id;

    private String phone;

    private Long articleId;

    private Integer judge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Integer getJudge() {
        return judge;
    }

    public void setJudge(Integer judge) {
        this.judge = judge;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", articleId=" + articleId +
                ", judge=" + judge +
                '}';
    }
}