package com.softserve.pageobj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GreencityPage extends TopPart {

    private WebElement ubsLink;
    private WebElement econewsLink;

    public GreencityPage(WebDriver driver) {
        super(driver);
        ubsLink = driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/ubs']"));
        econewsLink = driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/news']"));
    }

    // Atomic Operation
}
