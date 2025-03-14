package util;

import model.User;
import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {

    public static User generateRandomUser() {
        String email = generateRandomEmail();
        String password = "password";
        String name = "Username";
        return new User(email, password, name);
    }

    private static String generateRandomEmail() {
        String randomString = RandomStringUtils.randomAlphanumeric(10);
        return "test-" + randomString + "@example.com";
    }
}


