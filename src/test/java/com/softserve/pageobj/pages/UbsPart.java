package com.softserve.pageobj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class UbsPart extends TopPart {

    private WebElement greenCityLink;

    public UbsPart(WebDriver driver) {
        super(driver);
        greenCityLink = driver.findElement(By.cssSelector("div.header_navigation-menu-ubs a[href*='/greenCity']"));
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

    // Busness Operation

}

