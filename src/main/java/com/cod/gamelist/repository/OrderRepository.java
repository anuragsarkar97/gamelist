package com.cod.gamelist.repository;

import com.cod.gamelist.model.Order;
import com.cod.gamelist.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {

    Page<Order> findByUserId(String userId, PageRequest pageRequest);
}
