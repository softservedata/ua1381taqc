package com.softserve.param;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GreencityParLoginTest {
    private final Long IMPLICITLY_WAIT_SECONDS = 4L;

    private Dotenv dotenv;
    private WebDriver driver;
    private JavascriptExecutor javascriptExecutor;

    private void openBrowser() {
        switch (dotenv.get("browser.name")) {
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "Chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
    }

    private String getUrl() {
        String result = dotenv.get("base.url");
        if (result == null) {
            result = "https://www.greencity.cx.ua/#/ubs";
        }
        System.out.println("result = " + result);
        return result;
    }

    @BeforeAll
    public void setup() {
        dotenv = Dotenv.load();
        openBrowser();
        //
        //WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        //
        //driver = new ChromeDriver();
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
        driver.navigate().to(getUrl()); // Add History
        Thread.sleep(1000); // For Presentation
    }

    @AfterEach
    public void tearThis() throws InterruptedException {
        // Delete Session
        Thread.sleep(4000); // For Presentation
    }


    private static Stream<Arguments> userProvider() {
        String password = System.getenv().get("GREENCITY_PASSWORD");
        return Stream.of(
                //Arguments.of("tyv09754@zslsz.com", "Qwerty")
                Arguments.of("tyv09754@zslsz.com", password)
        );
    }

    //@Test
    @ParameterizedTest(name = "{index} => email={0}, password={1}")
    @MethodSource("userProvider")
    public void checkSignin(String email, String password) throws InterruptedException {
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
        driver.findElement(By.id("email")).sendKeys(email);
        Thread.sleep(1000); // For Presentation
        //
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        Thread.sleep(1000); // For Presentation
        //
        javascriptExecutor.executeScript("document.querySelector('button.ubsStyle').removeAttribute('disabled')");
        Thread.sleep(1000); // For Presentation
    }
}