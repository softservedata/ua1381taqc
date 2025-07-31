package com.softserve.pageobj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UbsGuestPage extends UbsPart implements SigninComponent {

    public UbsGuestPage(WebDriver driver) {
        super(driver);
    }

    // Atomic Operation

    // ubsUserNameButton
//    public WebElement getubsUserNameButton() {
//        return driver.findElement(By.cssSelector(LANGUAGE_OPTION_LINK_CSS));
//    }
//
//    public String getubsUserNameButtonText() {
//        return getubsUserNameButton().getText().trim();
//    }
//
//    public void clickubsUserNameButton() {
//        getubsUserNameButton().click();
//    }


    // Busness Operation

    public UbsGuestPage switchToEnLanguage() {
        super.switchEnLanguage();
        return new UbsGuestPage(driver);
    }

    public UbsGuestPage switchToUaLanguage() {
        super.switchUaLanguage();
        return new UbsGuestPage(driver);
    }

    public SiginPage gotoSiginPage() {
        logger.debug("gotoSiginPage() started");
        clickSigninLink(driver);
        return new SiginPage(driver);
    }

    public GreencityGuestPage gotoUbsGuestPage() {
        clickGreenCityLink();
        return new GreencityGuestPage(driver);
    }
}
