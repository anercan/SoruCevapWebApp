package com.anercan.sorucevap.web;

public class UserLogInController {
    private static UserLogInController ourInstance = new UserLogInController();

    public static UserLogInController getInstance() {
        return ourInstance;
    }

    private UserLogInController() {
    }
}
