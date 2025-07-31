package com.softserve.pageobj.pages;

import org.openqa.selenium.WebDriver;

public class GreencityGuestPage extends GreencityPart implements SigninComponent {

    public GreencityGuestPage(WebDriver driver) {
        super(driver);
    }

    // Atomic Operation

    // Busness Operation

    public GreencityGuestPage switchToEnLanguage() {
        super.switchEnLanguage();
        return new GreencityGuestPage(driver);
    }

    public GreencityGuestPage switchToUaLanguage() {
        super.switchUaLanguage();
        return new GreencityGuestPage(driver);
    }

    public SiginPage gotoSiginPage() {
        logger.debug("gotoSiginPage() started");
        clickSigninLink(driver);
        return new SiginPage(driver);
    }

    public UbsGuestPage gotoUbsGuestPage() {
        clickUbsLink();
        return new UbsGuestPage(driver);
    }

}
