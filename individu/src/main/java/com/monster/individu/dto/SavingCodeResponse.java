package com.monster.individu.dto;

public enum SavingCodeResponse {
    OK("00"),
    BAD_REQUEST("01"),
    USER_EXIST("02"),
    EMAIL_EXIST("05"),
    TECHNICAL_ERROR("03"),
    MESSAGING_EXCEPTION("04");

    public String code;


    SavingCodeResponse(String code) {
        this.code = code;
    }
}
