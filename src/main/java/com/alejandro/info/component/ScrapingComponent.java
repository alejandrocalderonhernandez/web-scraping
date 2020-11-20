package com.alejandro.info.component;

import com.alejandro.info.util.NumberUtil;
import com.alejandro.info.util.WebConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ScrapingComponent {

    private final static Logger logger = LoggerFactory.getLogger(ScrapingComponent.class);

    @Autowired
    private WebDriver driver;

    @PostConstruct
    private void getPage(){
        try {
            this.driver.get(WebConstants.BASE_URL);
        } catch (Exception e) {
            logger.warn("Cant found page: " + e.getMessage());
            throw new NoSuchElementException("Error with driver");
        }
    }

    public void resetPage(){
        try {
            this.driver.get(WebConstants.BASE_URL);
        } catch (Exception e) {
            logger.warn("Cant found page: " + e.getMessage());
            throw new NoSuchElementException("Error with driver");
        }

    }

    public Integer getElementValueById(String id) {
        try {
            return NumberUtil.toNumber(this.driver.findElement(By.cssSelector(id)).getText());
        } catch (Exception e) {
            logger.warn("Cant found element: " + e.getMessage());
            throw new NoSuchElementException("Error with driver");
        }
    }

    public void clickIntoElement(String id) {
        try {
            this.driver.findElement(By.cssSelector(id)).click();
        } catch (Exception e) {
            logger.warn("Cant click element: " + e.getMessage());
            throw new NoSuchElementException("Error with driver");
        }
    }


    public void closeDriver() {
        try {
            this.driver.close();
        } catch (Exception e) {
            logger.warn("Cant close driver: " + e.getMessage());
            throw new NoSuchElementException("Error with driver");
        }
    }
}
