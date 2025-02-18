package com.developersuraj.journalApp.controller;

import com.developersuraj.journalApp.entity.UserEntity;
import com.developersuraj.journalApp.service.UserDetailsServiceimpl;
import com.developersuraj.journalApp.service.UserEntryService;
import com.developersuraj.journalApp.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceimpl userDetailsServiceimpl;

    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public void signup(@RequestBody UserEntity user){
        userEntryService.saveNewEntry(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEntity user){

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName() , user.getPassword()));

            UserDetails userDetails = userDetailsServiceimpl.loadUserByUsername(user.getUserName());

            //JWT
            String jwt = jwtUtil.generateToken(userDetails.getUsername());

            return new ResponseEntity<>(jwt , HttpStatus.OK);
        }catch (Exception e){
            log.error("Error "+ e.getMessage());
            return new ResponseEntity<>("Incorrect username password" , HttpStatus.NOT_FOUND);
        }

    }
}
