package com.cod.gamelist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public  class ResponseModel<T> implements Serializable {
    private T data;
    private String exception;
}
