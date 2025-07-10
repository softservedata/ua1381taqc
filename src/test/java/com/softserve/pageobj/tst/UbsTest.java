package com.softserve.pageobj.tst;

import com.softserve.pageobj.pages.SiginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


public class UbsTest extends TestGreencityRunner {

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
