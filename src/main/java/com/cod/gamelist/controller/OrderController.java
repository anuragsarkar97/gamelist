package com.cod.gamelist.controller;

import com.cod.gamelist.model.Inventory;
import com.cod.gamelist.model.Order;
import com.cod.gamelist.model.PurchaseRequest;
import com.cod.gamelist.model.ResponseModel;
import com.cod.gamelist.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/purchase")
    public ResponseEntity<ResponseModel> createOrder(@RequestBody PurchaseRequest purchaseRequest) {
        try {
            Order order = orderService.createOrder(purchaseRequest.getUserId(), purchaseRequest.getInventoryId());
            if (order == null) {
                return ResponseEntity.ok().body(ResponseModel.builder().exception("Balance is low or error in inventory data").build());
            }
            return ResponseEntity.ok().body(ResponseModel.builder().data(order).build());
        } catch (Exception e) {
            return ResponseEntity.ok().body(ResponseModel.builder().exception(e.getMessage()).build());
        }
    }

    @GetMapping("/purchase")
    public ResponseEntity<ResponseModel> getOrder(@RequestParam("userId") String userId, @RequestParam("limit") Integer limit,
                                                  @RequestParam("page") Integer page) {
        try {
            List<Order> orders = orderService.fetchOrderList(userId, page, limit);
            return ResponseEntity.ok().body(ResponseModel.builder().data(orders).build());
        } catch (Exception e) {
            return ResponseEntity.ok().body(ResponseModel.builder().exception(e.getMessage()).build());
        }
    }
}
