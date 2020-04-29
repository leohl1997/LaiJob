package com.laioffer.githubexample.util;
import android.content.Context;
import android.location.LocationManager;
public class Config {

    public static String username = "leo";
    private static Config instance = null;

    public static String schoolStartData;
    public static String schoolName;
    public static String schoolEndData;

    public static String userId;
    public static String firstName;
    public static String lastName;
    public static String dataOfBirth;
    public static String address;
    public static String phone;
    public static String email;
    public static double latitude;
    public static double longitude;
    public static String jobTitle;
    public static String company;
    public static String jobStartDate;
    public static String jobEndDate;

    public String getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static Config getInstance() {
        return instance;
    }

    private Config(String userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static Config getInstance(String userId, String firstName, String lastName) {
        if (instance == null) {
            instance = new Config(userId, firstName, lastName);
        }

        return instance;
    }


}
