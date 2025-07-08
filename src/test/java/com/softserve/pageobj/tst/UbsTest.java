package com.softserve.pageobj.tst;

import com.softserve.pageobj.pages.SiginPage;
import com.softserve.pageobj.pages.UbsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UbsTest {

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

    private UbsPage loadApplication() throws InterruptedException{
        driver.navigate().to("https://www.pick-up.city/#/ubs");
        Thread.sleep(1000); // For Presentation
        return new UbsPage(driver);
    }

    private static Stream<Arguments> loginProvider() {
        System.out.println("\tArguments sumProvider done");
        return Stream.of(
                Arguments.of("lsd09559@kisoq.com", "Qwerty_12")
        );
    }

    @ParameterizedTest(name = "{index} => email={0}, password={1}")
    @MethodSource("loginProvider")
    public void checkUnsucessfulSignin(String invalidEmail, String invalidPassword) throws InterruptedException {
        SiginPage siginPage = loadApplication()
                //.switchEnLanguage()
                .switchUaLanguage()
                .gotoSiginPage()
                .UnsuccessfulSigninGreencity(invalidEmail, invalidPassword);
        //
        Assertions.assertTrue(siginPage.isAlertPasswordLabelPresent());
        Assertions.assertEquals(SiginPage.INVALID_PASSWORD_UA, siginPage.getAlertPasswordLabelText());
    }
}
