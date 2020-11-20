package com.alejandro.info.jobs;

import com.alejandro.info.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SheduledJob {
    //@Scheduled(cron = "0 0/5 * * * *")s
    private HTMLBuilder builder;
    private Set<String> mails;

    @Autowired
    public SheduledJob(HTMLBuilder builder, Set<String> mails) {
        this.builder = builder;
        this.mails = mails;
    }

    private final static Logger logger = LoggerFactory.getLogger(SheduledJob.class);


    @Scheduled(cron = "0 0 22 * * ?")
    public void getInfo(){
        System.out.println("Start job");
        String body = builder.buildHTML();
        MailUtil m = new MailUtil();
        m.sendMails(this.mails, body);
        System.out.println("End job");
    }
}
