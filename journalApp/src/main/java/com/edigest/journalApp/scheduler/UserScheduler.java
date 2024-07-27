package com.edigest.journalApp.scheduler;

import com.edigest.journalApp.cache.AppCache;
import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.enums.Sentiment;
import com.edigest.journalApp.repository.UserRepositoryImpl;
import com.edigest.journalApp.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserScheduler {


    @Autowired
    private UserRepositoryImpl userRepositoryImpl;



    @Autowired
    private AppCache appCache;
    @Autowired
    private EmailService emailService;

    @Scheduled(cron="0 0 0 9 * * SUN")
    public void fetchUsersAndSendSaMail(){

        List<User> users=userRepositoryImpl.getUserForSA();

        for(User user : users){
          List<JournalEntry>  journalEntries=user.getJournalEntries();
          List<Sentiment> sentiments=journalEntries.stream().filter(x->x.getDate()
                           .isAfter(LocalDateTime.now()
                                   .minus(7, ChronoUnit.DAYS))).map(x->x.getSentiment())
                  .collect(Collectors.toList());
 HashMap<Sentiment,Integer> sentimentCount =new HashMap<>();
 for(Sentiment sentiment : sentiments) {
     if (sentiment != null) {
         sentimentCount.put(sentiment, sentimentCount.getOrDefault(sentiment, 0) + 1);
     }
 }
     Sentiment mostFrequentSentiment=null;
     int max=Integer.MIN_VALUE;
     for(Sentiment count : sentimentCount.keySet()){
     if(sentimentCount.get(count)>max){
         mostFrequentSentiment=count;
         max=sentimentCount.get(count);
     }
     }
 if(mostFrequentSentiment!=null) {
     try {
         emailService.sendMail(user.getEmail(), "Sentiment for Last 7 days", mostFrequentSentiment.toString());
     }catch (Exception e){
         System.out.println(e.getMessage());
         log.error("Some Error while sending Sentiment for Last 7 days");
     }
 }
 }
        }


    @Scheduled(cron = "0 0/10 * 1/1 * ? *")
    public void clearAppCache(){
        appCache.init();
    }
}
