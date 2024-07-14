package com.edigest.journalApp.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.repository.JournalEntryRepository;

@Component
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
        System.out.println(e);
      }
   }
   public void saveEntry(JournalEntry journalEntry){
    journalEntryRepository.save(journalEntry);
     }
   public List<JournalEntry> getAll(){
    return journalEntryRepository.findAll();
   }
    
   public Optional<JournalEntry> findById(ObjectId id){
    return journalEntryRepository.findById(id);
   }

   public void deleteById(ObjectId id,String userName){
   User user = userService.findByUserName(userName); 
   for(int i=0;i<user.getJournalEntries().size();i++){
      if(user.getJournalEntries().get(i).getId().equals(id)){
         user.getJournalEntries().remove(i);
      }
   }
    journalEntryRepository.deleteById(id);
    userService.saveUser(user);
   }

}
