package com.softserve.pageobj.tst;

import com.softserve.pageobj.data.User;
import com.softserve.pageobj.data.UserRepository;
import com.softserve.pageobj.pages.SiginPage;
import com.softserve.pageobj.pages.UbsPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


public class UbsTest extends TestGreencityRunner {

    private static Stream<Arguments> unsucessfulLoginProvider() {
        System.out.println("\tArguments sumProvider done");
        return Stream.of(
                //Arguments.of("lsd09559@kisoq.com", "Qwerty_12")
                //Arguments.of(new User(59,"Qwerty","lsd09559@kisoq.com", "Qwerty_12"))
                //Arguments.of(UserRepository.getInvalidUser()),
                Arguments.of(UserRepository.getInvalidUser())
        );
    }

    //@ParameterizedTest(name = "{index} => email={0}, password={1}")
    @ParameterizedTest(name = "{index} => invalidUser={0}")
    @MethodSource("unsucessfulLoginProvider")
    //public void checkUnsucessfulSignin(String invalidEmail, String invalidPassword) throws InterruptedException {
    public void checkUnsucessfulSignin(User invalidUser) throws InterruptedException {
        logger.info("checkUnsucessfulSignin()  started, invalidUser = " + invalidUser);
        SiginPage siginPage = loadApplication()
                //.switchEnLanguage()
                .switchUaLanguage()
                .gotoSiginPage()
                .unsuccessfulSigninGreencity(invalidUser);
        //
        Assertions.assertTrue(siginPage.isAlertPasswordLabelPresent());
        //Assertions.assertFalse(siginPage.isAlertPasswordLabelPresent()); // fail
        Assertions.assertEquals(SiginPage.INVALID_PASSWORD_UA, siginPage.getAlertPasswordLabelText());
    }

    private static Stream<Arguments> sucessfulSigninProvider() {
        return Stream.of(
                Arguments.of(UserRepository.getValidUser())
        );
    }

    @ParameterizedTest(name = "{index} => validUser={0}")
    @MethodSource("sucessfulSigninProvider")
    public void checkSucessfulSignin(User validUser) throws InterruptedException {
        logger.info("checkSucessfulSignin()  started, validUser = " + validUser);
        UbsPage ubsPage = loadApplication()
                .switchEnLanguage()
                .gotoSiginPage()
                .successfulSigninUbs(validUser);
        //
        Assertions.assertEquals(validUser.getName(), ubsPage.getubsUserNameButtonText());
    }
}
