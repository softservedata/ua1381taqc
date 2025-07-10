package com.softserve.pageobj.tst;

import com.softserve.pageobj.pages.UbsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class TestGreencityRunner {

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
        //driver.navigate().to("https://www.greencity.cx.ua/#/ubs"); // Add History
        Thread.sleep(1000); // For Presentation
    }

    @AfterEach
    public void tearThis() throws InterruptedException {
        // Delete Session
        Thread.sleep(4000); // For Presentation
    }

    protected UbsPage loadApplication() throws InterruptedException{
        driver.navigate().to("https://www.pick-up.city/#/ubs");
        Thread.sleep(1000); // For Presentation
        return new UbsPage(driver);
    }

}
