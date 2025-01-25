package org.spring.authenticationservice.Utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    public static String getUsername(){
        Authentication authentication = getAuthentication();
        if (authentication != null){
            return authentication.getName();
        }
        return null;
    }

    public static String getCurrentRole() {
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            return authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst().orElse(null); // first role
        }
        return null;
    }
}
