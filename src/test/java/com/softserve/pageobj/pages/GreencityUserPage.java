package com.softserve.pageobj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GreencityUserPage extends GreencityPart implements ProfileComponent {

    private WebElement ubsLink;
    private WebElement econewsLink;

    public GreencityUserPage(WebDriver driver) {
        super(driver);
        //econewsLink = driver.findElement(By.cssSelector("div.header_navigation-menu a[href*='/news']"));
    }

    // Atomic Operation

    // Busness Operation

    public GreencityUserPage switchToEnLanguage() {
        super.switchEnLanguage();
        return new GreencityUserPage(driver);
    }

    public GreencityUserPage switchToUaLanguage() {
        super.switchUaLanguage();
        return new GreencityUserPage(driver);
    }

    public UbsUserPage gotoUbsUserPage() {
        clickUbsLink();
        return new UbsUserPage(driver);
    }
}
