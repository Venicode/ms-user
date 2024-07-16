package com.ms.user.controllers;

import com.ms.user.domains.User;
import com.ms.user.dtos.UserDto;
import com.ms.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody @Valid UserDto userDto){
        return this.userService.registerNewUser(userDto);
    }
    @GetMapping("/confirm/{idUser}")
    public ResponseEntity<Object> confirmUser(@PathVariable UUID idUser){
        return this.userService.confirmUser(idUser);
    }
}
