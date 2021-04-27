package com.xm.qimanwang.entity;

public class CommentTwo {
    private Long id;

    private Long pid;

    private Long articleid;

    private Integer answerid;

    private String answername;

    private Integer respondentid;

    private String respondentname;

    private String commentdate;

    private Integer likes;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getArticleid() {
        return articleid;
    }

    public void setArticleid(Long articleid) {
        this.articleid = articleid;
    }

    public Integer getAnswerid() {
        return answerid;
    }

    public void setAnswerid(Integer answerid) {
        this.answerid = answerid;
    }

    public Integer getRespondentid() {
        return respondentid;
    }

    public void setRespondentid(Integer respondentid) {
        this.respondentid = respondentid;
    }

    public String getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(String commentdate) {
        this.commentdate = commentdate == null ? null : commentdate.trim();
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getAnswername() {
        return answername;
    }

    public void setAnswername(String answername) {
        this.answername = answername;
    }

    public String getRespondentname() {
        return respondentname;
    }

    public void setRespondentname(String respondentname) {
        this.respondentname = respondentname;
    }

    @Override
    public String toString() {
        return "CommentTwo{" +
                "id=" + id +
                ", pid=" + pid +
                ", articleid=" + articleid +
                ", answerid=" + answerid +
                ", answername='" + answername + '\'' +
                ", respondentid=" + respondentid +
                ", respondentname='" + respondentname + '\'' +
                ", commentdate='" + commentdate + '\'' +
                ", likes=" + likes +
                ", content='" + content + '\'' +
                '}';
    }
}