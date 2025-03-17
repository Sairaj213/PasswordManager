package utils;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordUtils {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{}|;:,.<>?";
    private static final Random RANDOM = new SecureRandom();

    public static String generatePassword(int length, boolean useLower, boolean useUpper, boolean useDigits, boolean useSpecial) {
        if (length <= 0) return "";
        
        String valid = "";
        if (useLower) valid += LOWER;
        if (useUpper) valid += UPPER;
        if (useDigits) valid += DIGITS;
        if (useSpecial) valid += SPECIAL;
        if (valid.isEmpty()) return "";
        
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(valid.charAt(RANDOM.nextInt(valid.length())));
        return sb.toString();
    }

    public static boolean isStrong(String pwd) {
        if (pwd == null || pwd.length() < 8) return false;
        boolean lower = false, upper = false, digit = false, special = false;
        for (char c : pwd.toCharArray()) {
            if (Character.isLowerCase(c)) lower = true;
            else if (Character.isUpperCase(c)) upper = true;
            else if (Character.isDigit(c)) digit = true;
            else special = true;
        }
        return lower && upper && digit && special;
    }
}
