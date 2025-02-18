package com.developersuraj.journalApp.scheduling;

import com.developersuraj.journalApp.entity.JournalEntry;
import com.developersuraj.journalApp.entity.Sentement;
import com.developersuraj.journalApp.entity.UserEntity;
import com.developersuraj.journalApp.repository.UserEntryRepositoryIMP;
import com.developersuraj.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduling {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserEntryRepositoryIMP userEntryRepositoryIMP;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersSendMail(){
        List<UserEntity> users = userEntryRepositoryIMP.getUserForSA();
        for(UserEntity user : users){

            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<Sentement> sentements = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentement()).collect(Collectors.toList());
            Map<Sentement , Integer> sentementCount = new HashMap<>();

            for(Sentement sentement : sentements){
                sentementCount.put(sentement , sentementCount.getOrDefault(sentement , 0) + 1);
            }
            Sentement mostFrequentSentement = null;
            int maxCount  = 0;

            for(Map.Entry<Sentement , Integer> entry : sentementCount.entrySet()) {

                if(entry.getValue() > maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentement = entry.getKey();
                }

            }
            if(mostFrequentSentement != null){
                emailService.sendEmail(user.getEmail() , "Sentiment for last 7 days" , mostFrequentSentement.toString());
            }

        }
    }
}
