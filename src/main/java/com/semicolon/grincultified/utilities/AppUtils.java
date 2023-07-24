package com.semicolon.grincultified.utilities;

import java.util.List;

public class AppUtils {
    public static final String SYSTEM_MAIL = "aliyahrenike@gmail.com";
    public static final String CHECK_YOUR_MAIL_FOR_YOUR_OTP = "Check your mail for your otp!";
    public static final String CULTIFY_ADMIN_INVITATION = "CULTIFY-ADMIN INVITATION";
    public static final String INVITATION_SENT_SUCCESSFULLY = "Invitation sent successfully";
    public static final String INVALID_EMAIL = "Invalid email";
    public static final String ADMIN_INVITATION_MAIL_TEMPLATE = """
            Dear %s,
            
            You have been invited to be an admin on Cultify platform. Kindly click below link to get started.
            
            %s
            """;
    public static final String REGISTRATION_OTP = "REGISTRATION OTP";
    public static final String NO_INVESTMENTS_YET = "No investments yet";
    public static final String USER_DOES_NOT_EXIST = "User does not exist";
    public static final String USER_UPDATED_SUCCESSFULLY = "User updated successfully";
    public static final String USER = "user";
    public static final String OTP_TOKEN = "Your otp is: %s";
    public static final String INVESTOR_ALREADY_EXIST = "Account with this email already exist";
    public static final String REGISTERED_SUCCESSFULLY = "Registered successfully";
    public static final String INCORRECT_OTP= "Incorrect otp!";
    public static final String MESSAGE= "message";
    public static final String TEMPORARY_INVESTOR_DOES_NOT_EXIST= "Temporary investor does not exist!";
    public static final String TEMPORARY_INVESTOR_ALREADY_EXIST= "Temporary investor already exist!";
    public static final String FARMER_REGISTRATION_SUCCESSFUL= "Farmer registration successful";
    public static final String CREATED = "201";
    public static final String OK = "200";
    public static final String EMAIL_VALUE = "email";
    public static final String CULTIFY = "cultify";
    public static Long SIXTY = 60L;
    public static String ADMIN_REGISTRATION_PAGE_URL = "http://localhost:3000/admin/registration/";
    public static final String NO_INVITATION_SENT_YET = "%, No Registration Invitation Email Sent Yet";
    public static final String INVITATION_NOT_FOUND = "Invitation not found";
    public static final String ADMIN_NOT_FOUND = "Admin not found";
    public static final String ADMIN_ALREADY_EXISTS = "User with this email already exists";
    public static final String INVESTOR_ALREADY_EXISTS = "User with this email already exists";
    public static final String ADMIN_DOES_NOT_EXIST = "Admin does not exists";
    public static final String INVESTOR_DOES_NOT_EXIST = "Investor does not exists";
    public static final String INVITATION_REGISTERED = "Invitation registered";
    public static final String INVITATION_ALREADY_REGISTERED = "Invitation already registered";
    public static final String YOU_HAVE_ENTERED_AN_INVALID_OTP = "You have entered an invalid otp!";
    public static final String ADMIN_INVITATION_NOT_FOUND = "Admin invitation not found";
    public static final String INITIAL_REGISTRATION_NOT_FOUND_FOR = "Initial registration not found for %s";
    public static final String INVALID_TOKEN = "Invalid token";
    public static final String ROLES_VALUE = "roles";
    public static final String EMPTY_SPACE_VALUE = " ";
    public static final String AUTHENTICATION_FAILED_FOR_USER_WITH_EMAIL = "Authentication failed for cultifyUser with email %s";
    public static final String ACCESS_TOKEN_VALUE = "access_token";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ERROR_VALUE = "error";
    public static final String CLAIM_VALUE = "claim";
    public static final String USER_WITH_EMAIL_NOT_FOUND = "User with email %s not found";
    public static final String LOGIN_ENDPOINT = "/semicolon/cultify/v1/api/login";
    public static final String INVESTMENT_CREATION_ENDPOINT = "/semicolon/cultify/v1/api/investment/initiateInvestment";
    public static final String OTP_VERIFICATION_ENDPOINT = "/semicolon/cultify/v1/api/investor/confirmRegistration";
    public static final String INVESTOR_REGISTRATION_API_VALUE = "/semicolon/cultify/v1/api/investor/registration";
    public static final String ADMIN_REGISTRATION_API_VALUE = "/semicolon/cultify/v1/api/admin/registration";
    public static final String ADMIN_ENDPOINTS = "/semicolon/cultify/v1/api/admin/**";
    public static List<String> getAuthWhiteList(){
        return List.of(
                INVESTOR_REGISTRATION_API_VALUE, ADMIN_REGISTRATION_API_VALUE, LOGIN_ENDPOINT
        );
    }
}
