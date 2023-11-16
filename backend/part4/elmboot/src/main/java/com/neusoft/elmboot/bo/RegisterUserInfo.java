package com.neusoft.elmboot.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterUserInfo {
    private String username;
    private String password;
    private String userImg;
    private Integer userSex;
}
