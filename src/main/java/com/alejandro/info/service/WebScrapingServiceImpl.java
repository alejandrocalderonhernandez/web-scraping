package com.alejandro.info.service;

import com.alejandro.info.component.ScrapingComponent;
import com.alejandro.info.model.DataModel;
import com.alejandro.info.util.ThreadUtil;
import com.alejandro.info.util.WebConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebScrapingServiceImpl implements IWebScraping {

    private final static Logger logger = LoggerFactory.getLogger(WebScrapingServiceImpl.class);

    @Autowired
    private ScrapingComponent scraping;

    private Integer yesterdayActiveCasesMexico = 378285;
    private Integer yesterdayActiveCasesEdoMex = 42372;
    private Integer yesterdayActiveCasesCDMX = 66444;
    private Integer yesterdayActiveCasesAzcapotzalco = 3861;

    @Override
    public DataModel getData() {
        DataModel result = new DataModel();
        try {
            result.setTotalCasesMexico(scraping.getElementValueById(WebConstants.CONFIRMED_NUMBER));
            ThreadUtil.sleepSmall();
            result.setActiveCasesMexico(scraping.getElementValueById(WebConstants.ACTIVE_NUMBER));
            ThreadUtil.sleepSmall();
            result.setNewCasesPerDayMex(result.getTotalCasesMexico() - this.yesterdayActiveCasesMexico);
            ThreadUtil.sleepSmall();
            scraping.clickIntoElement(WebConstants.CDMX);
            ThreadUtil.sleepMedium();

            result.setTotalCasesCDMX(scraping.getElementValueById(WebConstants.CONFIRMED_NUMBER));
            ThreadUtil.sleepSmall();
            result.setActiveCasesCDMX(scraping.getElementValueById(WebConstants.ACTIVE_NUMBER));
            ThreadUtil.sleepSmall();
            result.setNewCasesPerDayCDMX(result.getTotalCasesCDMX() - this.yesterdayActiveCasesCDMX);
            ThreadUtil.sleepSmall();
            scraping.clickIntoElement(WebConstants.EDO_MEX);
            ThreadUtil.sleepMedium();

            result.setTotalCasesEdoMex(scraping.getElementValueById(WebConstants.CONFIRMED_NUMBER));
            ThreadUtil.sleepSmall();
            result.setActiveCasesEdoMex(scraping.getElementValueById(WebConstants.ACTIVE_NUMBER));
            ThreadUtil.sleepSmall();
            result.setNewCasesPerDayEdoMex(result.getTotalCasesEdoMex() - this.yesterdayActiveCasesEdoMex);
            ThreadUtil.sleepSmall();
            scraping.clickIntoElement(WebConstants.AZCAPOTZALCO);
            ThreadUtil.sleepMedium();

            result.setTotalCasesAzcapotzalco(scraping.getElementValueById(WebConstants.CONFIRMED_NUMBER));
            ThreadUtil.sleepSmall();
            result.setActiveCasesAzcapotzalco(scraping.getElementValueById(WebConstants.ACTIVE_NUMBER));
            ThreadUtil.sleepSmall();
            result.setNewCasesPerAzcapotzalco(result.getTotalCasesAzcapotzalco() - this.yesterdayActiveCasesAzcapotzalco);
            ThreadUtil.sleepSmall();

            this.yesterdayActiveCasesMexico = result.getActiveCasesMexico();
            this.yesterdayActiveCasesCDMX = result.getActiveCasesCDMX();
            this.yesterdayActiveCasesEdoMex = result.getActiveCasesEdoMex();
            this.yesterdayActiveCasesAzcapotzalco = result.getActiveCasesAzcapotzalco();

            this.scraping.resetPage();

        } catch (Exception e){
            logger.error("Error to execute", e);
            this.scraping.closeDriver();
        }

        return result;
    }
}
