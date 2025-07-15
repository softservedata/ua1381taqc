package com.softserve.pageobj.tools;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalStorageJS {
    protected final Logger logger = LoggerFactory.getLogger(LocalStorageJS.class);
    //
    private JavascriptExecutor javascriptExecutor;

    public LocalStorageJS(WebDriver webDriver) {
        this.javascriptExecutor = (JavascriptExecutor) webDriver;
    }

    public void clearHiddenWebElement(String cssLocator, String attributeName) {
        javascriptExecutor.executeScript(String
                .format("document.querySelector('%s').removeAttribute('%s')", cssLocator, attributeName));
    }

    public void removeViewState() {
        try {
            clearHiddenWebElement("#__VIEWSTATE", "value");
        } catch (Exception e) {
            logger.warn("Element #__VIEWSTATE not found");
            // Continue
        }
    }


    public void clearLocalStorage() {
        javascriptExecutor.executeScript(String.format("window.localStorage.clear();"));
    }

    public void removeItemFromLocalStorage(String item) {
        javascriptExecutor.executeScript(String.format("window.localStorage.removeItem('%s');", item));
    }

    public void removeAccessToken() {
        removeItemFromLocalStorage("accessToken");
    }

    public void removeRefreshToken() {
        removeItemFromLocalStorage("refreshToken");
    }
}
