package com.developersuraj.journalApp.entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class UserEntity {

    @Id
    private ObjectId id;
    @Indexed(unique = true) // For every unique userName/It automatically does not create a uniqe one we have to create it
    @NonNull
    private String userName;
    private String email;
    private boolean sentimentAnalysis;
    @NonNull
    private String password;
    @DBRef //Work as a forging key it holds the address of other data's id
    private List<JournalEntry> journalEntries = new ArrayList<>();
    //Roles :- what the user authorized to do.
    private List<String> roles;


}
