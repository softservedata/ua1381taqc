package com.softserve.selen;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GreencityLoginTest {
    private final Long IMPLICITLY_WAIT_SECONDS = 4L;

    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;

    @BeforeAll
    public void setup() {
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        //
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
        driver.manage().window().maximize();
        //driver.manage().window().setSize(new Dimension(1440, 798));
        //
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    @AfterAll
    public void tear() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    public void setupThis() throws InterruptedException {
        //driver.get("https://www.greencity.cx.ua/#/ubs");
        driver.navigate().to("https://www.greencity.cx.ua/#/ubs"); // Add History
        Thread.sleep(1000); // For Presentation
    }

    @AfterEach
    public void tearThis() throws InterruptedException {
        // Delete Session
        Thread.sleep(4000); // For Presentation
    }

    @Test
    public void checkSignin() throws InterruptedException {
        System.out.println("\t\t@Test testSelen()");
        //
        driver.findElement(By.cssSelector(".ubsHomepage")).click();
        Thread.sleep(1000); // For Presentation
        //
        //driver.findElement(By.cssSelector("div.main-content.app-container img.ubs-header-sing-in-img.ng-star-inserted")).click();
        driver.findElement(By.cssSelector("img.ubs-header-sing-in-img")).click();
        Thread.sleep(1000); // For Presentation
        //
        List<WebElement> iframe = driver.findElements(By.cssSelector("iframe"));
        System.out.println("\t\t\t\tiframe.size() = " + iframe.size());
        Thread.sleep(1000); // For Presentation
        if (iframe.size() > 0) {
            driver.switchTo().frame(iframe.get(0));
            List<WebElement> popupButton = driver.findElements(By.id("close"));
            System.out.println("\t\t\t\tpopupButton.size() = " + popupButton.size());
            if (popupButton.size() > 0) {
                popupButton.get(0).click();
            }
            driver.switchTo().defaultContent();
        }
        Thread.sleep(1000); // For Presentation
        //
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("tyv09754@zslsz.com");
        Thread.sleep(1000); // For Presentation
        //
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Qwerty");
        Thread.sleep(1000); // For Presentation
        //
        javascriptExecutor.executeScript("document.querySelector('button.ubsStyle').removeAttribute('disabled')");
        Thread.sleep(1000); // For Presentation
    }
}