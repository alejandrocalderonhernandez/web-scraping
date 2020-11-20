package com.alejandro.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/addMail")
public class MainController {

    @Autowired
    private Set<String> mails;

    @GetMapping
    public ResponseEntity<String> addMail(@RequestParam String mail){
        if(mail.contains("@") && mail.contains(".com")){
            this.mails.add(mail);
            return ResponseEntity.ok().body(this.mails.toString());
        }
        return ResponseEntity.badRequest().build();
    }

}
