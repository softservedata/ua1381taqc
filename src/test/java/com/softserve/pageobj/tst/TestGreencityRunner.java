package com.softserve.pageobj.tst;

import com.softserve.pageobj.pages.UbsPage;
import com.softserve.pageobj.tools.LocalStorageJS;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(RunnerExtension.class)
public abstract class TestGreencityRunner {

    private final String TIME_TEMPLATE = "yyyy-MM-dd_HH-mm-ss-S";
    private final Long IMPLICITLY_WAIT_SECONDS = 4L;

    private WebDriver driver;
    private LocalStorageJS localStorageJS;

    // Add test name
    private void takeScreenShot() {
        //logger.debug("Start takeScreenShot()");
        //
        //String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_TEMPLATE);
        String currentTime = localDate.format(formatter);
        //
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File("./" + currentTime + "_screenshot.png"));
        } catch (IOException e) {
            // Log.error
            throw new RuntimeException(e);
        }
    }

    // TODO Get Actual Page
    private void takePageSource() {
        //logger.debug("Start takePageSource()");
        //
        String currentTime = new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
        String pageSource = driver.getPageSource();
        byte[] strToBytes = pageSource.getBytes();
        Path path = Paths.get("./" + currentTime + "_" + "_source.html.txt");
        try {
            Files.write(path, strToBytes, StandardOpenOption.CREATE);
        } catch (IOException e) {
            // Log.error
            throw new RuntimeException(e);
        }
    }

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
        localStorageJS = new LocalStorageJS(driver);
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
    public void tearThis(TestInfo testInfo) throws InterruptedException {
        if (!RunnerExtension.isTestSuccessful) {
            // Log.error
            // logger.error("Test_Display_Name = " + testInfo.getDisplayName() + " failed");
            //logger.error("Test_Name = " + testInfo.getTestMethod() + " failed");
            //
            System.out.println("\t\t\tTest_Name = " + testInfo.getDisplayName() + " fail");
            System.out.println("\t\t\tTest_Method = " + testInfo.getTestMethod() + " fail");
            //
            takeScreenShot();
            takePageSource();
        }
        // Delete Session
        //
        // Clear Cookies
        driver.manage().deleteAllCookies();
        //
        // Clear Local Storage
        localStorageJS.removeAccessToken();
        localStorageJS.removeRefreshToken();
        //
        // Clear Hidden ViewState
        localStorageJS.removeViewState();
        //
        Thread.sleep(4000); // For Presentation
    }

    protected UbsPage loadApplication() throws InterruptedException {
        driver.navigate().to("https://www.pick-up.city/#/ubs");
        Thread.sleep(1000); // For Presentation
        return new UbsPage(driver);
    }

}
