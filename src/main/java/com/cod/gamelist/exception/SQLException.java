package com.cod.gamelist.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SQLException extends Exception {
    public SQLException(Exception e) {
        super(e);
        log.error("SQLException {}", e.getMessage());
    }
}
