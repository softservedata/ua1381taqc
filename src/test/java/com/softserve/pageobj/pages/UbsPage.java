package com.softserve.pageobj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UbsPage extends TopPart {
    private final String LANGUAGE_OPTION_LINK_CSS = "ul[aria-label*='language'] li[role='menuitem'] > span";
    private final String LANGUAGE_EN_XPATH = "//span[text()='EN']";
    private final String LANGUAGE_UA_XPATH = "//span[text()='UA']";

    private WebElement greenCityLink;
    private WebElement languageLink;
    private WebElement ubsUserNameButton;

    public UbsPage(WebDriver driver) {
        super(driver);
        //greenCityLink = driver.findElement(By.cssSelector("div.header_navigation-menu-ubs a[href*='/greenCity']"));
        languageLink = driver.findElement(By.cssSelector("ul[aria-label*='language'] li[role='option'] > span"));
        ubsUserNameButton = driver.findElement(By.cssSelector("li.ubs-user-name"));
    }

    // Atomic Operation

    // greenCityLink
    public WebElement getGreenCityLink() {
        return greenCityLink;
    }

    public String getGreenCityLinkText() {
        return getGreenCityLink().getText().trim();
    }

    public void clickGreenCityLink() {
        getGreenCityLink().click();
    }

    // languageLink
    public WebElement getLanguageLink() {
        return languageLink;
    }

    public String getLanguageLinkText() {
        return getLanguageLink().getText().trim();
    }

    public void clickLanguageLink() {
        getLanguageLink().click();
    }

    // languageOptionLink
    public WebElement getLanguageOptionLink() {
        return driver.findElement(By.cssSelector(LANGUAGE_OPTION_LINK_CSS));
    }

    public String getLanguageOptionLinkText() {
        return getLanguageOptionLink().getText().trim();
    }

    public void clickLanguageOptionLink() {
        getLanguageOptionLink().click();
    }

    // ubsUserNameButton
    public WebElement getubsUserNameButton() {
        return driver.findElement(By.cssSelector(LANGUAGE_OPTION_LINK_CSS));
    }

    public String getubsUserNameButtonText() {
        return getubsUserNameButton().getText().trim();
    }

    public void clickubsUserNameButton() {
        getubsUserNameButton().click();
    }


    // Busness Operation

    public UbsPage switchEnLanguage() {
        if (getLanguageLinkText().equalsIgnoreCase("EN")) {
            return this;
        } else {
            clickLanguageLink();
            clickLanguageOptionLink();
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector(LANGUAGE_EN_XPATH))
            );
            return new UbsPage(driver);
        }
    }

    public UbsPage switchUaLanguage() {
        if (getLanguageLinkText().equalsIgnoreCase("UA")) {
            return this;
        } else {
            clickLanguageLink();
            clickLanguageOptionLink();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            new WebDriverWait(driver, Duration.ofSeconds(10)).until(
//                    ExpectedConditions.presenceOfElementLocated(By.cssSelector(LANGUAGE_UA_XPATH))
//            );
            return new UbsPage(driver);
        }
    }
}
