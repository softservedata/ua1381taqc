package com.softserve.pageobj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TopPart {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected WebDriver driver;
    //
    private WebElement signinLink;

    public TopPart(WebDriver driver) {
        this.driver = driver;
        signinLink = driver.findElement(By.cssSelector("img.ubs-header-sing-in-img"));
    }

    // Atomic Operation

    // signinLink
    public WebElement getSigninLink() {
        return signinLink;
    }

    public void clickSigninLink() {
        getSigninLink().click();
    }

    // Busness Operation

    public SiginPage gotoSiginPage() {
        logger.debug("gotoSiginPage() started");
        clickSigninLink();
        return new SiginPage(driver);
    }
}
