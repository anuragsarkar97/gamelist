package com.cod.gamelist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventory")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Float price;
    private String status;
    private String type;
    private String externalId;

}
