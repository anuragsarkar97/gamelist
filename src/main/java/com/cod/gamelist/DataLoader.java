package com.cod.gamelist;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class DataLoader {

    @Autowired
    private Environment environment;

    @PostConstruct
    public void init() {
        log.info("Starting Data loader process");
    }
}