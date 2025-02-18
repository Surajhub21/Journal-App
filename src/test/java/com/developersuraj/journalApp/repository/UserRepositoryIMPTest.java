package com.developersuraj.journalApp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryIMPTest {

    @Autowired
    public UserEntryRepositoryIMP userEntryRepositoryIMP;


    @Test
    public void testMonoTemplate(){
        userEntryRepositoryIMP.getUserForSA();
    }
}
