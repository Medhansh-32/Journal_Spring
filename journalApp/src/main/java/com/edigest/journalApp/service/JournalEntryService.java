package com.edigest.journalApp.service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.JournalEntryRepository;

@Component
@Slf4j
public class JournalEntryService {
    
   @Autowired
   private UserService userService;

   @Autowired //dependency Injection
   private JournalEntryRepository journalEntryRepository;



   @Transactional
   public void saveEntry(JournalEntry journalEntry,String userName){
      try {
         User user = userService.findByUserName(userName); 
         journalEntry.setDate(LocalDateTime.now());
         JournalEntry saved=journalEntryRepository.save(journalEntry);
         user.getJournalEntries().add(saved);
         userService.saveUser(user);
              
      } catch (Exception e) {
         log.error(e.getMessage());
      }
   }
   public void saveEntry(JournalEntry journalEntry){
      journalEntry.setDate(LocalDateTime.now());
    journalEntryRepository.save(journalEntry);
     }
   public List<JournalEntry> getAll(){
    return journalEntryRepository.findAll();
   }
    
   public Optional<JournalEntry> findById(ObjectId id){
    return journalEntryRepository.findById(id);
   }
@Transactional
   public boolean deleteById(ObjectId id,String userName){
      boolean removed=false;
      try {
   User user = userService.findByUserName(userName); 
   for(int i=0;i<user.getJournalEntries().size();i++){
      if(user.getJournalEntries().get(i).getId().equals(id)){
         user.getJournalEntries().remove(i);
         journalEntryRepository.deleteById(id);
         userService.saveUser(user);
         removed=true;
         return removed;
      }
   }
      } catch (Exception e) {
      throw new RuntimeException("An Error Occured..");  
      
      }
   return removed;

   }
}