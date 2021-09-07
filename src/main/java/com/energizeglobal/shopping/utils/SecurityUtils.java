package com.energizeglobal.shopping.utils;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * using security utils to get user details from session
 * which will be completed after security module has added to project
 *
 * @author Omid Rostami
 */

public class SecurityUtils {

    private SecurityUtils() {
    }


    public static String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = null;
        if (authentication != null) {
            userName = authentication.getName();
        }
        return userName;
    }
}
