package com.cod.gamelist.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SupakiResult {
    @JsonAlias("id")
    private String id;
    @JsonAlias("name")
    private String name;
    @JsonAlias("price")
    private Integer price;

}