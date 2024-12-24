package com.developersuraj.journalApp.controller;

import com.developersuraj.journalApp.entity.UserEntity;
import com.developersuraj.journalApp.repository.UserEntryRepository;
import com.developersuraj.journalApp.service.UserEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    @Autowired
    private UserEntryService userEntryService;
    @Autowired
    private UserEntryRepository userEntryRepository;

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        UserEntity userInDB = userEntryService.findByUserName(userName);
        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());
        userEntryService.saveNewEntry(userInDB);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteUser() {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        userEntryRepository.deleteByUserName(authentication.getName());

        return new ResponseEntity<>(HttpStatus.OK);

    }


}
//20:00