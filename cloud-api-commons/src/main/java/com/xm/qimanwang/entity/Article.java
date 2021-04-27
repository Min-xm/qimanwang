package com.xm.qimanwang.entity;

public class Article {
    private Long articleid;

    private String author;

    private String originalauthor;

    private String title;

    private String tags;

    private String publishdate;

    private String updatedate;

    private String articleurl;

    private Integer likes;

    private String content;

    public Long getArticleid() {
        return articleid;
    }

    public void setArticleid(Long articleid) {
        this.articleid = articleid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getOriginalauthor() {
        return originalauthor;
    }

    public void setOriginalauthor(String originalauthor) {
        this.originalauthor = originalauthor == null ? null : originalauthor.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
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

    public String getArticleurl() {
        return articleurl;
    }

    public void setArticleurl(String articleurl) {
        this.articleurl = articleurl == null ? null : articleurl.trim();
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

    @Override
    public String toString() {
        return "Article{" +
                "articleid=" + articleid +
                ", author='" + author + '\'' +
                ", originalauthor='" + originalauthor + '\'' +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", publishdate='" + publishdate + '\'' +
                ", updatedate='" + updatedate + '\'' +
                ", articleurl='" + articleurl + '\'' +
                ", likes=" + likes +
                ", content='" + content + '\'' +
                '}';
    }
}