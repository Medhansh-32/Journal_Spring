package com.edigest.journalApp.controller;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.JournalEntryService;
import com.edigest.journalApp.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesofUser(){
      org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournalEntry> allData= user.getJournalEntries();
        if(!allData.isEmpty() && allData!=null){
            return new ResponseEntity<>(allData,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
      try{ 
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        journalEntryService.saveEntry(myEntry,userName);
        return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
    }catch(Exception e){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
            }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntry(@PathVariable ObjectId myId){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournalEntry> collect= user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry=journalEntryService.findById(myId);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
            }
        }
      
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId myId){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        if(journalEntryService.deleteById(myId,userName)==true){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       
    }

    @PatchMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> updateJournalEntry(
        @PathVariable ObjectId myId,
        @RequestBody JournalEntry newEntry
        ){
            org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
            User user=userService.findByUserName(userName);
        List<JournalEntry> collect= user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry=journalEntryService.findById(myId);
            if(journalEntry.isPresent()){
                JournalEntry old=journalEntryService.findById(myId).orElse(null);
                if(old!=null){
                 if(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")){
                    old.setTitle(newEntry.getTitle());
                 }
                 if(newEntry.getContent()!=null && !newEntry.getContent().equals("")){
                    old.setContent(newEntry.getContent());
                 }
                 journalEntryService.saveEntry(old);
                 return new ResponseEntity<>(old,HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       
    }
}
