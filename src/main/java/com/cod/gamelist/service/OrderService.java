package com.cod.gamelist.service;

import com.cod.gamelist.exception.SQLException;
import com.cod.gamelist.model.Inventory;
import com.cod.gamelist.model.Order;
import com.cod.gamelist.model.User;
import com.cod.gamelist.model.Wallet;
import com.cod.gamelist.repository.InventoryRepository;
import com.cod.gamelist.repository.OrderRepository;
import com.cod.gamelist.repository.UserRepository;
import com.cod.gamelist.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Order createOrder(String userId, String inventoryId) throws SQLException {
        try {
            Optional<Inventory> inventory = inventoryRepository.findById(inventoryId);
            if (inventory.isEmpty() || Objects.equals(inventory.get().getStatus(), "SOLD")) return null;
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty()) return null;
            Optional<Wallet> wallet = walletRepository.findById(user.get().getWalletId());
            if (wallet.isEmpty() || wallet.get().getBalance() < inventory.get().getPrice()) return null;
            Order order = Order.builder()
                    .inventoryId(inventoryId)
                    .userId(userId)
                    .walletId(user.get().getWalletId())
                    .totalCost(inventory.get().getPrice())
                    .build();
            Order orderCreated = orderRepository.save(order);
            wallet.get().setBalance(wallet.get().getBalance() - inventory.get().getPrice());
            walletRepository.save(wallet.get());
            inventory.get().setStatus("SOLD");
            inventoryRepository.save(inventory.get());
            return orderCreated;
        } catch (Exception e) {
            log.error("create order exception {}", e.getMessage());
            throw new SQLException(e);
        }
    }

    public List<Order> fetchOrderList(String userId, Integer page, Integer size) throws SQLException {
        try {
            Page<Order> byUserId = orderRepository.findByUserId(userId, PageRequest.of(page, size));
            return byUserId.stream().toList();
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

}
