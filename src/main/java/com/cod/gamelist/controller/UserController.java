package com.cod.gamelist.controller;

import com.cod.gamelist.model.User;
import com.cod.gamelist.model.CreateUserRequest;
import com.cod.gamelist.model.ResponseModel;
import com.cod.gamelist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Basic CRUD model for User
 * no authentication/authorization present in this scope
 * GET-PUT-PATCH-DELETE
 * */
@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<ResponseModel> createUser(@RequestBody CreateUserRequest userCreateRequest) {
        try {
            User user = userService.createUser(userCreateRequest.getUser(), userCreateRequest.getBalance());
            if (user != null) {
                return ResponseEntity
                        .ok()
                        .body(ResponseModel
                                .builder().data(user)
                                .build());
            } else {
                return ResponseEntity
                        .badRequest()
                        .body(ResponseModel
                                .builder()
                                .exception("Email Already exist")
                                .build());
            }
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseModel
                            .builder()
                            .data(null)
                            .exception(e.getMessage())
                            .build());
        }
    }

    @DeleteMapping("/user/:email")
    public ResponseEntity<ResponseModel> deleteUser(@PathVariable("email") String emailAddress) {
        try {
            Boolean isDeleted = userService.deleteUser(emailAddress);
            return ResponseEntity
                    .ok(ResponseModel
                            .builder()
                            .data(isDeleted)
                            .build());
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(ResponseModel
                            .builder()
                            .exception(e.getMessage())
                            .build());
        }
    }
}
