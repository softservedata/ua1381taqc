package com.softserve.pageobj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ProfileComponent {

    String PROFILE_BUTTON = "ul.nav-global-button li[class*='user-name']";

    default WebElement getProfileButton(WebDriver driver) {
        return driver.findElement(By.cssSelector(PROFILE_BUTTON));
    }

    default String getProfileButtonText(WebDriver driver) {
        return getProfileButton(driver).getText();
    }

    default void clickProfileButton(WebDriver driver) {
        getProfileButton(driver).click();
    }
}
