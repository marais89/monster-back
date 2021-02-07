package individu.dto;

public enum SavingCodeResponse {
    OK("00"),
    BAD_REQUEST("01"),
    USER_EXISTE("02"),
    TECHNICAL_ERROR("03"),
    MESSAGING_EXCEPTION("04");

    public String code;


    SavingCodeResponse(String code) {
        this.code = code;
    }
}
