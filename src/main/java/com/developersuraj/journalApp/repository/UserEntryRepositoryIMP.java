package com.developersuraj.journalApp.repository;


import com.developersuraj.journalApp.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserEntryRepositoryIMP{

    //mongoTemplate use for perform query on data

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserEntity> getUserForSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is("suraj"));
        List<UserEntity> users = mongoTemplate.find(query, UserEntity.class);
        return users;
    }

}
