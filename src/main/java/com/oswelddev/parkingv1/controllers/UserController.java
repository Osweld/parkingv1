package com.oswelddev.parkingv1.controllers;

import com.oswelddev.parkingv1.models.dto.ChangePassword;
import com.oswelddev.parkingv1.models.entity.User;
import com.oswelddev.parkingv1.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    ResponseEntity<Page<User>> getUserPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        return new ResponseEntity<>(userService.getUserPage(PageRequest.of(page,size)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    ResponseEntity<User> getUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable Long id){
        return new ResponseEntity<>(userService.updateUser(user,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<User> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.deleteUserById(id),HttpStatus.OK);
    }

    @PutMapping("/change-password")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<User> updateUser(@RequestBody ChangePassword changePassword){
        return new ResponseEntity<>(userService.changePassword(changePassword),HttpStatus.OK);
    }


}
