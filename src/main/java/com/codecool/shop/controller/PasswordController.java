package com.codecool.shop.controller;

/**
 * Created by hamargyuri on 2016. 12. 13..
 */
abstract class PasswordController {

    public static String hashPassword(String rawPwd) throws PasswordStorage.CannotPerformOperationException {
        return PasswordStorage.createHash(rawPwd);
    }

    public boolean verifyPassword(String rawPwd, String hash) throws PasswordStorage.InvalidHashException,
            PasswordStorage.CannotPerformOperationException {
        return PasswordStorage.verifyPassword(rawPwd, hash);
    }
}
