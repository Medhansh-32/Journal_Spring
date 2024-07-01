package com.edigest.journalApp.service;
import com.edigest.journalApp.repository.JournalEntryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.edigest.journalApp.entity.JournalEntry;

@Component
public class JournalEntryService {
    
   @Autowired //dependency Injection
   private JournalEntryRepository journalEntryRepository; 

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

   public void deleteById(ObjectId id){
    journalEntryRepository.deleteById(id);
   }

}
