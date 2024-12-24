package com.developersuraj.journalApp.service;

import com.developersuraj.journalApp.entity.JournalEntry;
import com.developersuraj.journalApp.entity.UserEntity;
import com.developersuraj.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component //After Declearing the component if will considers a bean so spring can use it from anywhere
public class JournalEntryService {

    @Autowired  //injection of implementation
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserEntryService userEntryService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry,
                          String userName){
        try {
            UserEntity user = userEntryService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
//        user.setUserName(null); //Because of @transactional if the function give error it don't store the previous code if it works the entier code willl execute else nothing will execute.
            user.getJournalEntries().add(saved);
            userEntryService.saveEntry(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entry, ", e);
        }

    }
    public void saveEntry(JournalEntry journalEntry){ //overloading
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){

        boolean removed = false;
        try {

            UserEntity user = userEntryService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x ->
                    x.getId().equals(id)
            );

            if (removed) {
                userEntryService.saveEntry(user);  //Delete the Reference id and update in the same location
                journalEntryRepository.deleteById(id);
            }
        }

        catch (Exception e){

            throw new RuntimeException("Error Occur during deleting journal entity :- "+e);
        }

        return removed;
    }


}

// Controller --> service --> Repository --
// -->