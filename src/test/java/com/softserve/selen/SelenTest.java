package com.softserve.selen;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SelenTest {
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;

    private WebDriver driver;

    @BeforeAll
    public void setup() {
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        //
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
        driver.manage().window().maximize();
    }

    @AfterAll
    public void tear() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    public void setupThis() throws InterruptedException {
        driver.get("https://www.bing.com/");
        Thread.sleep(1000); // For Presentation
    }

    @AfterEach
    public void tearThis() throws InterruptedException {
        // Delete Session
        Thread.sleep(4000); // For Presentation
    }

    @Test
    public void checkSearch() throws InterruptedException {
        WebElement div = driver.findElement(By.id("sb_form_c"));
        WebElement q = div.findElement(By.xpath("./div/textarea")); // XPath Ok
        q.sendKeys("mac");
        Thread.sleep(1000); // For Presentation
        //
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        Thread.sleep(1000); // For Presentation
        //
        Assertions.assertEquals("mac - Search", driver.getTitle());
    }
}
