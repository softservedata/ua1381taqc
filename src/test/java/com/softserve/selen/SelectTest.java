package com.softserve.selen;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SelectTest {

    private final Long IMPLICITLY_WAIT_SECONDS = 10L;

    private WebDriver driver;

    @BeforeAll
    public void setup() {
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        //
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        //driver = new HtmlUnitDriver();
        //
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS)); // 0 by default
        driver.manage().window().maximize();
        //driver.manage().window().setSize(new Dimension(1440, 798));

    }

    @AfterAll
    public void tear() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeEach
    public void setupThis() throws InterruptedException {
        String pagePath = this.getClass().getResource("/page.html").getPath();
        System.out.println("pagePath = " + pagePath);
        //driver.get("https://www.bing.com/");
        driver.navigate().to("file://" + pagePath); // Add History
        Thread.sleep(1000); // For Presentation
    }

    @AfterEach
    public void tearThis() throws InterruptedException {
        // Delete Session
        Thread.sleep(4000); // For Presentation
    }

    @Test
    public void checkSearch() throws InterruptedException {
        System.out.println("Start checkSearch()");
        //
        WebElement selectTag = driver.findElement(By.tagName("select"));
        selectTag.click();
        List<WebElement> allOptions = selectTag.findElements(By.tagName("option"));
        for (WebElement option : allOptions) {
            //if (option.getAttribute("value").equals("option2")) {
            if (option.getText().contains("3")) {
                option.click();
                selectTag.click();
                Thread.sleep(4000); // For Presentation
            }
        }
        //
        Select select = new Select(driver.findElement(By.tagName("select")));
        select.selectByVisibleText("Option 1");
        Thread.sleep(2000); // For Presentation
    }
}
