package com.developersuraj.journalApp.service;

import com.developersuraj.journalApp.entity.JournalEntry;
import com.developersuraj.journalApp.entity.UserEntity;
import com.developersuraj.journalApp.repository.JournalEntryRepository;
import com.developersuraj.journalApp.repository.UserEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component //After Declearing the component it will considers a bean so spring can use it from anywhere
@Slf4j
public class UserEntryService {

    @Autowired  //injection of implementation
    private UserEntryRepository userEntryRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //logging
//    private static final Logger logger = LoggerFactory.getLogger(UserEntryService.class);

    public void saveEntry(UserEntity user){
        userEntryRepository.save(user);
    }

    public void saveNewEntry(UserEntity user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userEntryRepository.save(user);
        }catch (Exception e){
            log.info("efverv");
            log.error("Error Occur {} :",user.getUserName() , e.getMessage());
            log.warn("efverv");
            log.debug("efverv"); //if we have to give debug and trace we will have to do some customizations to run debug and trace //in applicasiton.property
            log.trace("efverv");
        }
    }

    public void saveNewAdmin(UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER" , "ADMIN"));
        userEntryRepository.save(user);
    }

    public List<UserEntity> getAll(){
        return userEntryRepository.findAll();
    }

    public Optional<UserEntity> findById(ObjectId id){
        return userEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id){

        userEntryRepository.deleteById(id);
    }

    public UserEntity findByUserName(String userName){
        return userEntryRepository.findByUserName(userName);
    }

}

// Controller --> service --> Repository --
// -->