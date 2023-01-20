package com.cod.gamelist.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserPresentException extends Exception {
    public UserPresentException(String message) {
        super(message);
        log.error("User already present with email {}", message);
    }

    public UserPresentException() {
    }
}
