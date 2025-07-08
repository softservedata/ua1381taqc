package com.softserve.pageobj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class TopPart {

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
        clickSigninLink();
        return new SiginPage(driver);
    }
}
