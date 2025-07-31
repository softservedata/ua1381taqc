package com.softserve.pageobj.data;

import java.util.List;
import java.util.Random;

public final class UserRepository {

    private UserRepository() {
    }

    public static User getDefaultUser() {
        return getGreencityUser();
    }

    public static User getGreencityUser() {
        return null;
    }

    public static User getValidUser() {
        User user = new User();
        user.setUserId(59);
        user.setName("Qwerty");
        user.setEmail("xvr89922@toaik.com");
        user.setPassword("Qwerty_1");
        user.setSecretKey("UD~3tDW<$K.rEk$IELFTVQwWU$-tN%IX~q>`NuMpxhUMb$D");
        return user;
        //return new User(59,"Qwerty","lsd09559@kisoq.com", "Qwerty_12");
    }

    public static User getInvalidUser() {
        Random random = new Random();
        String number = String.valueOf(random.nextInt(10000));
        User user = new User();
        user.setUserId(59);
        user.setName("Qwerty");
        user.setEmail("lsd" + number + "@kisoq.com");
        user.setPassword("Qwerty_12");
        return user;
        //return new User(59,"Qwerty","lsd09559@kisoq.com", "Qwerty_12");
    }

    public static List<User> getDBUsers() {
        return null;
    }
}
