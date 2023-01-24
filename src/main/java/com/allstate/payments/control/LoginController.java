package com.allstate.payments.control;

import com.allstate.payments.data.UserRepository;
import com.allstate.payments.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public User login(@RequestBody Map<String,String> loginData) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        System.out.println(userDetails.getUsername());
        User user = userRepository.findByUsername(userDetails.getUsername()).get();
        return user;
    }
}
