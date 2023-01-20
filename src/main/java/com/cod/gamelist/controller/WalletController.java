package com.cod.gamelist.controller;

import com.cod.gamelist.model.Order;
import com.cod.gamelist.model.Wallet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class WalletController {

    @GetMapping("/wallet")
    public ResponseEntity<Wallet> getWallet() {
        return null;
    }

    @PatchMapping("/wallet")
    public ResponseEntity<Wallet> patchWallet() {
        return null;
    }
}
