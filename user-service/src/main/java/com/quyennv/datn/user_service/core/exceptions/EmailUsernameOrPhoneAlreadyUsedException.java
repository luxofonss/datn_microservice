package com.quyennv.datn.user_service.core.exceptions;

public class EmailUsernameOrPhoneAlreadyUsedException extends DomainException{
    public EmailUsernameOrPhoneAlreadyUsedException(String message) {
        super(message);
    }
}
