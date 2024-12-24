package com.developersuraj.journalApp.controller;

import com.developersuraj.journalApp.entity.UserEntity;
import com.developersuraj.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserEntryService userEntryService;

    @PostMapping("/create-user")
    public void createUser(@RequestBody UserEntity user){
        userEntryService.saveNewEntry(user);
    }
}
