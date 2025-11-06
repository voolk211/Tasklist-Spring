package org.example.tasklist.web.dto.auth;

import lombok.Data;

@Data
public class JwtResponse {

    private Long Id;
    private String username;
    private String accessToken;
    private String refreshToken;
}
