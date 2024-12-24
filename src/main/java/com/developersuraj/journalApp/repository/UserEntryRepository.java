package com.developersuraj.journalApp.repository;

import com.developersuraj.journalApp.entity.JournalEntry;
import com.developersuraj.journalApp.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserEntryRepository extends MongoRepository<UserEntity, ObjectId> {

    UserEntity findByUserName(String username);

    void deleteByUserName(String name);
}
