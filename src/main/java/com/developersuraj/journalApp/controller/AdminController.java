package com.developersuraj.journalApp.controller;

import com.developersuraj.journalApp.entity.UserEntity;
import com.developersuraj.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<UserEntity> all = userEntryService.getAll();

        if(all != null && !all.isEmpty()){
            return  new ResponseEntity<>(all , HttpStatus.ACCEPTED);
        }

        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add-new-admin")
    public void addNewAdmin(@RequestBody UserEntity user){

        userEntryService.saveNewAdmin(user);


    }
}
