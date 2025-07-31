package com.softserve.pageobj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UbsUserPage extends UbsPart implements ProfileComponent {

    private WebElement profileButton;

    public UbsUserPage(WebDriver driver) {
        super(driver);
        profileButton = driver.findElement(By.cssSelector("ul.nav-global-button"));
    }

    // Atomic Operation

    // Busness Operation

    public UbsUserPage switchToEnLanguage() {
        super.switchEnLanguage();
        return new UbsUserPage(driver);
    }

    public UbsUserPage switchToUaLanguage() {
        super.switchUaLanguage();
        return new UbsUserPage(driver);
    }

    public GreencityUserPage gotoGreencityUserPage() {
        clickGreenCityLink();
        return new GreencityUserPage(driver);
    }
}
