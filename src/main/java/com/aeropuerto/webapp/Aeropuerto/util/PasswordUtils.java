package com.aeropuerto.webapp.Aeropuerto.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {
    private static PasswordUtils instance;

    private PasswordUtils() {

    }

    public static PasswordUtils getInstance() {
        if (instance == null) {
            instance = new PasswordUtils();
        }
        return instance;
    }

    public String encrytedPassword(String pass) {
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }

    public boolean checkPassword(String pass, String encrytedPass) {
        return BCrypt.checkpw(pass, encrytedPass);
    }
}
