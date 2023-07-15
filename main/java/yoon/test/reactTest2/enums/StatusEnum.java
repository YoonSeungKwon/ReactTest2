package yoon.test.reactTest2.enums;

public enum StatusEnum {

    OK(200, "OK"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    NOT_FOUND(404, "NOT_FOUND"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR");

    private final int code;
    private final String key;

    StatusEnum(int code, String key){
        this.code = code;
        this.key = key;
    }
}
