package com.developersuraj.journalApp.controller;

import com.developersuraj.journalApp.entity.ConfigEntity;
import com.developersuraj.journalApp.repository.ConfigJournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private ConfigJournalRepository configJournalRepository;

    public Map<String , String> APP_CACHE;

    @PostConstruct
    public void init(){
        APP_CACHE = new HashMap<>();
        List<ConfigEntity> all = configJournalRepository.findAll();
        all.forEach( item ->
                APP_CACHE.put(item.getKey() , item.getValue())
        );
    }
}
