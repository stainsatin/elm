package com.neusoft.elmboot.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {
    public static String getUserId() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
