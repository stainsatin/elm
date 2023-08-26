package com.neusoft.elm.po;

public class User {

    private String userId;
    private String password;
    private String userName;
    private Integer userSex;
    private String userImg;
    private Integer delTag;


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public void setDelTag(Integer delTag) {
        this.delTag = delTag;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public String getUserImg() {
        return userImg;
    }

    public Integer getDelTag() {
        return delTag;
    }
}