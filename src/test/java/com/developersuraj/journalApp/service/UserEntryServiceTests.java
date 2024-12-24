package com.developersuraj.journalApp.service;

import com.developersuraj.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
public class UserEntryServiceTests {

    @Autowired
    private UserEntryRepository userEntryRepository;

    @Test
    public void testAdd(){
        assertEquals(4 , 2+2);
    }

//    @Disabled
//    @ParameterizedTest
//    @ValueSource(strings ={
//            "ram",
//            "surja",
//            "test",
//            "soiudn"
//    })
//   public void testFindByUserName(String userName){
//        assertNotNull(userEntryRepository.findByUserName(userName));
//    }

    //
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "5, 6, 12"
    })
    public void test(int a , int b , int expected){
        assertEquals(expected , a + b);
    }
}
