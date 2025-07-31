package com.softserve.pageobj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface SigninComponent {

    String SIGNIN_LINK = "img.ubs-header-sing-in-img"; // public static final

    // Atomic Operation

    // signinLink
    default WebElement getSigninLink(WebDriver driver) {
        return driver.findElement(By.cssSelector(SIGNIN_LINK));
    }

    default void clickSigninLink(WebDriver driver) {
        getSigninLink(driver).click();
    }

}
