package com.Chubb.EcommerceWebSite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RBACUtil {

    @Autowired
    private JwtGenerator jwtGenerator;

    public void verifyAccess(String token, String requiredRole) {
        String roles = jwtGenerator.extractRoles(token);

        if (roles == null || !roles.contains(requiredRole)) {
            throw new RuntimeException("Access Denied: Insufficient Role");
        }
    }
    public void verifyAccessWithId(String token, String requiredRole , String requiredId) {
        String roles = jwtGenerator.extractRoles(token);
        String id = jwtGenerator.extractId(token);
        if (roles == null || !roles.contains(requiredRole) || !id.contains(requiredId)) {
            throw new RuntimeException("Access Denied: Insufficient Role");
        }
    }
}
