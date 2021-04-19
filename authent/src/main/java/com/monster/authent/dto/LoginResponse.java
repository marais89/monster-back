package com.monster.authent.dto;

public class LoginResponse {


    public boolean isValidCredentials;
    public Boolean isUserActive;
    public String accessToken;
    public long failedTentativeCount;
}
