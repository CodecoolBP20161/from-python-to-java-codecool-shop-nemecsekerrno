package com.codecool.shop.controller;

/**
 * Created by hamargyuri on 2016. 12. 13..
 */
abstract class PasswordController {

    public static String hashPassword(String rawPwd) throws PasswordHandler.CannotPerformOperationException {
        return PasswordHandler.createHash(rawPwd);
    }

    public boolean verifyPassword(String rawPwd, String hash) throws PasswordHandler.InvalidHashException,
            PasswordHandler.CannotPerformOperationException {
        return PasswordHandler.verifyPassword(rawPwd, hash);
    }
}
