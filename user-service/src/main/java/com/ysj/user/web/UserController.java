package com.ysj.user.web;

import com.ysj.user.pojo.User;
import com.ysj.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    //注册用户
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid User user, @RequestParam("code") String code){
        userService.register(user,code);
        return ResponseEntity.ok().build();
    }

    //登录
    @PostMapping("/query")
    public ResponseEntity<User> query(@RequestParam("username") String username,@RequestParam("password") String password){
        return ResponseEntity.ok(userService.query(username,password));
    }
}
