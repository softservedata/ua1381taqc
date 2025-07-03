package com.softserve.selensearch;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DisplayElements {
    private static WebDriver driver;
    private boolean isSuccess;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //
//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();
        //
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // 0 by default
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void tear() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    public void setupThis() {
        driver.get("https://demo.opencart.com/index.php");
        isSuccess = false;
    }

    @AfterEach
    public void tearThis(TestInfo testInfo) throws Exception {
        if (!isSuccess) {
            System.out.println("\t\t\tTest_Name = " + testInfo.getDisplayName() + " fail");
            System.out.println("\t\t\tTest_Method = " + testInfo.getTestMethod() + " fail");
        }
    }

    @DisplayName("Should investigate elements")
    @Test
    public void testLoginVisible() throws InterruptedException {
        // Step
        System.out.println("[INFO] Start Test testLogin()");
        Thread.sleep(2000); // for presentation
        //
        WebElement code = driver.findElement(By.name("code"));
        System.out.println("[INFO] code isVisible isDisplayed = " + code.isDisplayed());
        System.out.println("[INFO] code isVisible isEnabled = " + code.isEnabled());
        Thread.sleep(2000); // for presentation
        //
        WebElement aboutUs = driver.findElement(By.linkText("About Us"));
        System.out.println("[INFO] aboutUs isVisible isDisplayed = " + aboutUs.isDisplayed());
        System.out.println("[INFO] aboutUs isVisible isEnabled = " + aboutUs.isEnabled());
        Thread.sleep(2000); // for presentation
        //
        //driver.findElement(By.cssSelector("div.nav.float-end a.dropdown-toggle[data-bs-toggle='dropdown']")).click();
        //WebElement login = driver.findElement(By.linkText("Login")); // Runtime Error
        WebElement login = driver.findElement(By.cssSelector("div.dropdown a[href*='account/login']"));
        System.out.println("[INFO] login isVisible isDisplayed = " + login.isDisplayed());
        System.out.println("[INFO] login isVisible isEnabled = " + login.isEnabled());
        Thread.sleep(2000); // for presentation
        //
        driver.findElement(By.cssSelector("div.nav.float-end a.dropdown-toggle[data-bs-toggle='dropdown']")).click();
        System.out.println("[INFO] My_Account Click done");
        Thread.sleep(2000); // for presentation
        //
        System.out.println("[INFO] After My_Account Click login isVisible isDisplayed = " + login.isDisplayed());
        System.out.println("[INFO] After My_Account Click login isVisible isEnabled = " + login.isEnabled());
        Thread.sleep(2000); // for presentation
        //
        Thread.sleep(4000); // for presentation
        Assertions.assertTrue(true);
        isSuccess = true;
    }

}
             