package com.softserve.pageobj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GreencityPart extends TopPart {

    private WebElement ecoNewsLink;
    private WebElement ubsLink;

    public GreencityPart(WebDriver driver) {
        super(driver);
        ecoNewsLink = driver.findElement(By.cssSelector("li.nav-left-list a[href='#/greenCity/news']"));
        ubsLink = driver.findElement(By.cssSelector("li.nav-left-list a[href='#/ubs']"));
    }

    // Atomic Operation

    // ecoNewsLink
    public WebElement getEcoNewsLink() {
        return ecoNewsLink;
    }

    public String getEcoNewsLinkText() {
        return getUbsLink().getText().trim();
    }

    public void clickEcoNewsLink() {
        getUbsLink().click();
    }

    // ubsLink
    public WebElement getUbsLink() {
        return ubsLink;
    }

    public String getUbsLinkText() {
        return getUbsLink().getText().trim();
    }

    public void clickUbsLink() {
        getUbsLink().click();
    }

    // Busness Operation
}
