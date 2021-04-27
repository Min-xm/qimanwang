package com.xm.qimanwang.entity;

public class Users {
    private Integer id;

    private String phone;

    private String username;

    private String password;

    private String gender;

    private String birthday;

    private String email;

    private String brief;

    private String imgurl;

    private String recently;

    private Integer power;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public String getRecently() {
        return recently;
    }

    public void setRecently(String recently) {
        this.recently = recently == null ? null : recently.trim();
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "{" +
                "'id':" + id +
                ",'phone':'" + phone + '\'' +
                ",'username':'" + username + '\'' +
                ",'password':'" + password + '\'' +
                ",'gender':'" + gender + '\'' +
                ",'birthday':'" + birthday + '\'' +
                ",'email':'" + email + '\'' +
                ",'brief':'" + brief + '\'' +
                ",'imgurl':'" + imgurl + '\'' +
                ",'recently':'" + recently + '\'' +
                ",'power':" + power +
                '}';
    }
}