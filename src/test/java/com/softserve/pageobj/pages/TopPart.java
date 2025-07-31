package com.softserve.pageobj.pages;

import com.softserve.pageobj.tst.TestGreencityRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class TopPart {
    private final String LANGUAGE_OPTION_LINK_CSS = "ul[aria-label*='language'] li[role='menuitem'] > span";
    private final String LANGUAGE_EN_XPATH = "//span[contains(text(),'E')]";
    private final String LANGUAGE_UA_XPATH = "//span[contains(text(),'U')]";

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    protected WebDriver driver;
    //
    private WebElement languageButton;

    public TopPart(WebDriver driver) {
        this.driver = driver;
        languageButton = driver.findElement(By.cssSelector("ul[aria-label*='language'] li.lang-option span"));
    }

    // Atomic Operation

    // languageButton
    public WebElement getLanguageButton() {
        return languageButton;
    }

    public String getLanguageButtonText() {
        return getLanguageButton().getText().trim();
    }

    public void clickLanguageButton() {
        getLanguageButton().click();
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

    // Busness Operation

    protected void switchEnLanguage() {
        if (getLanguageButtonText().equalsIgnoreCase("UA")) {
            clickLanguageButton();
            clickLanguageOptionLink();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector(LANGUAGE_EN_XPATH))
            );
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestGreencityRunner.IMPLICITLY_WAIT_SECONDS));
        }
    }

    protected void switchUaLanguage() {
        if (getLanguageButtonText().equalsIgnoreCase("EN")) {
            clickLanguageButton();
            clickLanguageOptionLink();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(
                    ExpectedConditions.presenceOfElementLocated(By.cssSelector(LANGUAGE_UA_XPATH))
            );
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestGreencityRunner.IMPLICITLY_WAIT_SECONDS));
        }
    }

}
