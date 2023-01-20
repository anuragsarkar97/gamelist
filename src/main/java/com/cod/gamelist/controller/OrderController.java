package com.cod.gamelist.controller;

import com.cod.gamelist.model.Inventory;
import com.cod.gamelist.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
public class OrderController {

    @PostMapping("/")
    public ResponseEntity<Boolean> createOrder() {
        return null;
    }

    @GetMapping("/")
    public ResponseEntity<Order> getOrder() {
        return null;
    }
}
