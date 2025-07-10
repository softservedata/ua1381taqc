package com.softserve.pageobj.pages;

import com.softserve.pageobj.data.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SiginPage {
    public static final String INVALID_PASSWORD_UA = "Введено невірний email або пароль";
    public static final String INVALID_PASSWORD_EN = "Bad email or password";

    private final String ALERT_PASSWORD_LABEL_CSS = "div.alert-general-error";

    private WebDriver driver;
    //
    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signinButton;

    public SiginPage(WebDriver driver) {
        this.driver = driver;
        emailField = driver.findElement(By.id("email"));
        passwordField = driver.findElement(By.id("password"));
        signinButton = driver.findElement(By.cssSelector("button[type='submit']"));
    }

    // Atomic Operation

    // emailField
    public WebElement getEmailField() {
        return emailField;
    }

    public void clickEmailField() {
        getEmailField().click();
    }

    public void clearEmailField() {
        getEmailField().clear();
    }

    public void sendKeysEmailField(String email) {
        getEmailField().sendKeys(email);
    }

    // passwordField
    public WebElement getPasswordField() {
        return passwordField;
    }

    public void clickPasswordField() {
        getPasswordField().click();
    }

    public void clearPasswordField() {
        getPasswordField().clear();
    }

    public void sendKeysPasswordField(String password) {
        getPasswordField().sendKeys(password);
    }

    // signinButton
    public WebElement getSigninButton() {
        return signinButton;
    }

    public void clickSigninButton() {
        getSigninButton().click();
    }

    // AlertPasswordLabel
    public boolean isAlertPasswordLabelPresent() {
        List<WebElement> validators = driver.findElements(By.cssSelector(ALERT_PASSWORD_LABEL_CSS));
        return validators.size() > 0;
    }

    public WebElement getAlertPasswordLabel() {
        return driver.findElement(By.cssSelector(ALERT_PASSWORD_LABEL_CSS));
    }

    public String getAlertPasswordLabelText() {
        return getAlertPasswordLabel().getText().trim();
    }

    // Busness Operation

    private void typeEmail(String email) {
        clickEmailField();
        clearEmailField();
        sendKeysEmailField(email);
    }

    private void typePassword(String password) {
        clickPasswordField();
        clearPasswordField();
        sendKeysPasswordField(password);
    }

    //private void signinForm(String email, String password) {
    private void signinForm(User user) {
        typeEmail(user.getEmail());
        typePassword(user.getPassword());
        clickSigninButton();
    }

    public UbsPage SuccessfulSigninUbs(User validUser) {
        signinForm(validUser);
        return new UbsPage(driver);
    }

    public GreencityPage SuccessfulSigninGreencity(User validUser) {
        signinForm(validUser);
        return new GreencityPage(driver);
    }

    // public SiginPage UnsuccessfulSigninGreencity(String invalidEmail, String invalidPassword) {
    public SiginPage UnsuccessfulSigninGreencity(User invalidUser) {
        signinForm(invalidUser);
        return new SiginPage(driver);
    }
}
