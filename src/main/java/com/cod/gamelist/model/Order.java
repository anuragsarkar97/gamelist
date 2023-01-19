package com.cod.gamelist.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String userId;
    private String walletId;
    private String inventoryId;
    private String quantity;
    private String unitCost;
    private String totalCost;
}
