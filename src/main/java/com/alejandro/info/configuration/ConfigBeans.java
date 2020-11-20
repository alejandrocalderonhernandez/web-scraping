package com.alejandro.info.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConfigBeans {

    private static final String CHROME = "webdriver.chrome.driver";
    //private static final String PATH_DRIVER = "/home/alejandro/Projects/chromedriver";
    private static final String PATH_DRIVER = "/home/ubuntu/resources/chromedriver";
    
    @Bean
    public Set<String> getMails(){
        Set<String> mails = new HashSet<>();
        mails.add("vanesadaymr@gmail.com");
        mails.add("alejandro950825@gmail.com");
        return mails;
    }

    @Bean
    public WebDriver getDriver(){
        System.setProperty(CHROME, PATH_DRIVER);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");
        return new ChromeDriver(options);
    }
}
