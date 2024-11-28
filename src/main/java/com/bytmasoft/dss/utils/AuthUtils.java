package com.bytmasoft.dss.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {

    public String getClientIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }


    public String getUsername() {
        if(SecurityContextHolder.getContext() != null)
            if (SecurityContextHolder.getContext().getAuthentication() != null)
                if (SecurityContextHolder.getContext().getAuthentication().getName() != null)
                    return SecurityContextHolder.getContext().getAuthentication().getName();
        return "Abakar";
    }

}
