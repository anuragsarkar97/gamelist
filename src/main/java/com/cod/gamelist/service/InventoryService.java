package com.cod.gamelist.service;

import com.cod.gamelist.exception.SQLException;
import com.cod.gamelist.model.Inventory;
import com.cod.gamelist.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> fetchInventory(Integer limit, Integer page) throws SQLException {
        try {
            Page<Inventory> inventoryResult = inventoryRepository
                    .findAllByQuantityGreaterThan(0, PageRequest.of(page, limit));
            return inventoryResult.stream().toList();
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
