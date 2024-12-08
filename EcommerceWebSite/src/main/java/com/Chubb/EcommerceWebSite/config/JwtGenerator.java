package com.Chubb.EcommerceWebSite.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtGenerator {

    private final String SECRET_KEY = "LRafUw0vOkhZwhHJ6XzDS/k5sHxeSGq/zw6JzhH6T1P+dsi/Lr3kZp4h6rTQqE+UrN2WfhMY6Int7PSHiYKB/j7MvH9UVGdIptkrry9cOTsX3Tqpg/dvKMBdX7M2o3O8DDq53mpDvg7yQ8kg17TO1m+Bm1OL7GacrtIQu8UXct71EjV31TAVDdTXzGTRdqSJW6s6plp4bEhS4JYem5WTG7GNHP5N5QB4U68+B0z0SutuN2XaPC+jbFX3kOQDCJAUiyGZFSBM2Pb6oKnQdrV7Br6rRQVQPZSJaYJQ7FapIRItTyMa+3Ijmm0VJRDQ2mnqkpB7A+K0W+x+J4sKntJSREM6ICfVvX63JWvgNr+maohcLU74odPQrbn/BknonjXKXnyr/VZ9z41/OsJKQQW5ADudiRwFyWSkJouxfpWWyX/GgbmmKa7fQVaDG7atzu2drDkV2EqNKD23Umdu8hoIxK2IrQc6j6m+Amk2tI6MG1VSeLBBkdtpbjeXojnr0sFwjw/Bt8fH20pB/Nn/ORTa3th5cRw3hqD47W195Wxh/B/xfmzadWRy9dBZmH7ScTVrSLAxzUlIocjfk5OvHeryCL48o/F3+ytaFn3otwTW6M01Cq0AGY9UFZrmcA/l2dqZFeFExXQZ/OowAhFniXscFzvUHkY0CJdl8YIu4X3xiVTxDDxEY5t03KeC2wbFzjhtPfri2xsKrjnfSVznIy22lgC/wn3l2wD64qEbWTwy/oUEGBLCtO79RnLTe8pnjypeHm/4o77xxzDvq1HwRa7vlvUIqW0mXubFQGXAeFaSqqIj/OeAFAGZGTJ4JgNxLQW/9e8BSOVMJWglby+8zeIeY0ZUw/70evZv+c1Zf1NZ9fffIDGhECl/oUi48513li7SPRBOfUoG3NJZjosUxUIv9uIgjs7VSAKX+NU5uCF37rYSW7GBraAxKgYHAhYVVreBFwD67/c9qgyWk/nxelZGsZRz9uaLOVSmlP9/POA29FbWVuO09k0kRS+gNSeFmquoDJkhI4AHxKo7NxvioUY49pJ/0kmRcjwHJJuDFONtMG1/mwLVLZ7fyLZLcOW8q/UYfxCfOoaPf2DZEg/C4+kNnHa/+4TIg7hTJ/az/K0QnrnXf7OfG508nAu+mjzTwcfoWkz9bgSodAb18kyyaYBxzxCjkPg3k37Z9R9xX7J115l7bFfdxCfZJ/9Rdy6vMd2s0WEcq36dxqGqAKlsDZlVNQ4bSfmpUBk//BX5mZjKje/0nTPFk2dDHs1QXPHNtx5O86DKmj6VPR7zKNOq7uljLIT0I/ux83Xn3QEtwi8wa2oEoGrTuGhbgxpUOdu/VOIaFV+ndFV+PypBE2o9yYwUTZ1OEdmPPQm7FJXWKQ0AsO4=\n";

    public String generateToken(String username, String roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date()) // Token issue date
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()) // Sign with secret key
                .compact();
    }

        public String extractRoles(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("roles", String.class); // Assuming roles are stored as a claim
    }
    public String extractId(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject(); // Extract the "sub" (username) claim
        if (username != null && username.contains(":")) {
            return username.split(":")[1]; // Extract the ID part after the colon
        }
        throw new RuntimeException("Invalid username format or ID not found");
    }

}
