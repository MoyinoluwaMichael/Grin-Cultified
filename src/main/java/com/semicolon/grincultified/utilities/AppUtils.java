package com.semicolon.grincultified.utilities;

import java.security.SecureRandom;

public class AppUtils {
    public static final String SYSTEM_MAIL = "aliyahrenike@gmail.com";
    public static final String OTP_MESSAGE = String.format("Your one-time password is %s", generateOtp()) ;
    public static final String CHECK_YOUR_MAIL_FOR_YOUR_OTP = "Check your mail for your otp!";
    public static final String REGISTRATION_OTP = "REGISTRATION OTP";

    private static String generateOtp() {
        SecureRandom secureRandom = new SecureRandom();
        int otpValue = secureRandom.nextInt(900000) + 100000;
        return String.valueOf(otpValue);
    }

}
