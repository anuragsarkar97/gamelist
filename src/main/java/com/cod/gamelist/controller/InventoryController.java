package com.cod.gamelist.controller;

import com.cod.gamelist.model.Inventory;
import com.cod.gamelist.model.ResponseModel;
import com.cod.gamelist.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/listings")
    public ResponseEntity<ResponseModel> getInventory(@RequestParam("limit") Integer limit,
                                                      @RequestParam("page") Integer page) {
        try {
            List<Inventory> inventories = inventoryService.fetchInventory(limit, page);
            return ResponseEntity.ok().body(ResponseModel.builder().data(inventories).build());
        } catch (Exception e) {
            return ResponseEntity.ok().body(ResponseModel.builder().exception(e.getMessage()).build());
        }
    }

}
