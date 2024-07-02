package com.edigest.journalApp.service;
import com.edigest.journalApp.repository.JournalEntryRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.edigest.journalApp.entity.*;

@Component
public class JournalEntryService {
    
   @Autowired
   private UserService userService;

   @Autowired //dependency Injection
   private JournalEntryRepository journalEntryRepository; 

   public void saveEntry(JournalEntry journalEntry,String userName){
    User user = userService.findByUserName(userName); 
    journalEntry.setDate(LocalDateTime.now());
    JournalEntry saved=journalEntryRepository.save(journalEntry);
    user.getJournalEntries().add(saved);
    userService.saveUser(user);
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
