package com.pgagtersales.pgaftersales.exceptions;

import lombok.Getter;

@Getter
public class UserServiceException extends RuntimeException {

    private static final Long serialVersionUID = 123425L;

    private String developerMessage;

    public UserServiceException(String usermessage, String developerMessage) {
        super(usermessage);
        this.developerMessage = developerMessage;
    }

}
