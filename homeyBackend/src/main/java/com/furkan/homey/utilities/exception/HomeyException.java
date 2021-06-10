package com.furkan.homey.utilities.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class HomeyException extends RuntimeException{

    private int code;
    private String message;
    private String description;
    private String[] fields;
    private String[] placeHolders;
    private HttpStatus httpStatus;

    protected static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public HomeyException(int code, String message, String description, String[] fields, String[] placeHolders, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.message = message;
        this.description = description;
        this.fields = fields;
        this.placeHolders = placeHolders;
        this.httpStatus = httpStatus;
    }

    public HomeyException(int code, String[] fields, String[] placeHolders) {
        this(code, null, null, fields, placeHolders, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public HomeyException(int code, String[] fields) {
        this(code, null, null, fields, null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public HomeyException(int code) {
        this(code, null, null, null, null, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public HomeyException(int code, HttpStatus httpStatus) {
        this(code, null, null, null, null, httpStatus);
    }
}
