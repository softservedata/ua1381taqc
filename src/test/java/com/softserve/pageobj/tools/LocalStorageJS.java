package com.softserve.pageobj.tools;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LocalStorageJS {
    private JavascriptExecutor javascriptExecutor;

    public LocalStorageJS(WebDriver webDriver) {
        this.javascriptExecutor = (JavascriptExecutor) webDriver;
    }

    public void clearHiddenWebElement(String cssLocator, String attributeName) {
        javascriptExecutor.executeScript(String
                .format("document.querySelector('%s').removeAttribute('%s')", cssLocator, attributeName));
    }

    public void removeViewState() {
        clearHiddenWebElement("#__VIEWSTATE", "value");
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
