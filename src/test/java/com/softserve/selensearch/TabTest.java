package com.softserve.selensearch;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TabTest {

    private static final Long ONE_SECOND_DELAY = 1000L;
    private static WebDriver driver;

    protected static void presentationSleep() {
        presentationSleep(1);
    }

    // Overload
    protected static void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public boolean isAlertPresent2() {
        try {
            driver.getTitle();
            return false;
        } catch (UnhandledAlertException e) {
            return true;
        }
    }

    public boolean alertIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            if (alert != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // 0 by default
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(100)); // 0 by default
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(180)); // 300 by default
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void tear() {
        presentationSleep(4); // For Presentation ONLY
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    public void setupThis() {
        driver.get("https://selenium.dev/downloads/");
        presentationSleep(4); // For Presentation ONLY
    }

    @AfterEach
    public void tearThis(TestInfo testInfo) {
        presentationSleep(); // For Presentation ONLY
    }

    @Test
    public void checkNewTab() {
        String originalWindow = driver.getWindowHandle();
        // Create new tab
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.bing.com/");
        presentationSleep(2); // For Presentation ONLY
        //
        System.out.println("Number of tabs = " + driver.getWindowHandles().size());
        driver.findElement(By.name("q")).sendKeys("mac" + Keys.ENTER);
        presentationSleep(2); // For Presentation ONLY
        //
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("alert('Welcome to TAQC');");
        presentationSleep(2); // For Presentation ONLY
        //
        for (String windowHandle : driver.getWindowHandles()) {
            System.out.println("windowHandle = " + windowHandle);
            driver.switchTo().window(windowHandle);
            presentationSleep(2); // For Presentation ONLY
            if (isAlertPresent()) {
            //if (alertIsDisplayed()) {
                driver.switchTo().alert().accept();
            }
            System.out.println("Title = " + driver.getTitle());
        }
        //
        for (String windowHandle : driver.getWindowHandles()) {
            if (originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                System.out.println("Return to Original tab");
                break;
            }
        }
        presentationSleep(2); // For Presentation ONLY
    }
}
