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
import com.edigest.journalApp.service.UserService;
import com.edigest.journalApp.entity.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;
    
    @GetMapping("{userName}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesofUser(@PathVariable String userName){
        User user=userService.findByUserName(userName);
        List<JournalEntry> allData= user.getJournalEntries();
        if(!allData.isEmpty() && allData!=null){
            return new ResponseEntity<>(allData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){
      try{ 
        journalEntryService.saveEntry(myEntry,userName);
        return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
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

    @DeleteMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId myId,@PathVariable String userName){
        journalEntryService.deleteById(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/id/{userName}/{myId}")
    public ResponseEntity<JournalEntry> updateJournalEntry(
        @PathVariable ObjectId myId,
        @RequestBody JournalEntry newEntry,
        @PathVariable String userName
        ){
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
