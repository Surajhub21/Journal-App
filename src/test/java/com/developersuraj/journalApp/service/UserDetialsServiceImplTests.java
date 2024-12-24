package com.developersuraj.journalApp.service;

import com.developersuraj.journalApp.entity.UserEntity;
import com.developersuraj.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.Disabled;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.when;

@Disabled
public class UserDetialsServiceImplTests {

    @Autowired
    private UserDetailsServiceimpl userDetailsServiceimpl;

    @Mock
    private UserEntryRepository userEntryRepository;

    void loadUserByUsername(){

        when(userEntryRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn((UserEntity) User.builder().username("ram").password("uyg").build());
        UserDetails user = userDetailsServiceimpl.loadUserByUsername("ram");

    }
}
