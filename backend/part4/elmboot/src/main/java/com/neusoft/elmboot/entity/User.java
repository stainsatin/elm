package com.neusoft.elmboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String userId;
    private String password;
    private String username;
    private Integer userSex;
    private String userImg;
    private Integer walletId;

    public User() {

    }
}