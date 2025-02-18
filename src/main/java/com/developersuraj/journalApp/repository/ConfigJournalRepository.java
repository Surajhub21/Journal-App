package com.developersuraj.journalApp.repository;

import com.developersuraj.journalApp.entity.ConfigEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ConfigJournalRepository extends MongoRepository<ConfigEntity, ObjectId> {


}
