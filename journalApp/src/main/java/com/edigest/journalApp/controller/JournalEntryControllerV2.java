package com.edigest.journalApp.controller;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.service.JournalEntryService;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    
    @Autowired
    private JournalEntryService journalEntryService;

    
    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAll(){
        List<JournalEntry> allData= journalEntryService.getAll();
        if(!allData.isEmpty() && allData!=null){
            return new ResponseEntity<>(allData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
      try{ 
        journalEntryService.saveEntry(myEntry);
        return new ResponseEntity<>(myEntry,HttpStatus.OK);
    }catch(Exception e){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
            }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntry(@PathVariable ObjectId myId){
        Optional<JournalEntry> currentEntry=journalEntryService.findById(myId);
        if(currentEntry.isPresent()){
            return new ResponseEntity<>(currentEntry.get(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> updateJournalEntry(@PathVariable ObjectId myId,@RequestBody JournalEntry newEntry){
        JournalEntry old=journalEntryService.findById(myId).orElse(null);
        if(old!=null){
         if(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")){
            old.setTitle(newEntry.getTitle());
         }
         if(newEntry.getContent()!=null && !newEntry.getContent().equals("")){
            old.setContent(newEntry.getContent());
         }
         journalEntryService.saveEntry(old);
         return new ResponseEntity<>(newEntry,HttpStatus.OK);
        }else{
      
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
}
