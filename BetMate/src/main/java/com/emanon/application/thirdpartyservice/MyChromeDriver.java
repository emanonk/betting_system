/*
 * Copyright Camelot Global. All rights reserved
 */
package com.emanon.application.thirdpartyservice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

/**
 *
 * @author mmkamm
 */
@Component
public class MyChromeDriver {

    private WebDriver driver;

    public WebDriver initChromeDriver() {

        final String binaryPath = "src/main/resources/chromedriver";

       System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
//        ChromeOptions chromeOpt= new ChromeOptions();
//        chromeOpt.setBinary(binaryPath);


        driver = new ChromeDriver();
        return driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver openUrl(String url) {
        driver.get(url);
        return driver;
    }

    public void killDriver() {
        driver.quit();
    }

}
