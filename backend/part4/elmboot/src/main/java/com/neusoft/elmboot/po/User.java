package com.neusoft.elmboot.po;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = -3565823820366948318L;
    private String userId;
    private String password;
    private String userName;
    private Integer userSex;
    private String userImg;
    private Integer delTag;
    private Integer walletId;

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

	public Integer getWalletId() {
		return walletId;
	}

	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}
}