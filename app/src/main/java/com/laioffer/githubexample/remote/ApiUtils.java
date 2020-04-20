package com.laioffer.githubexample.remote;

public class ApiUtils {
    public static final String BASE_URL = "http://ec2-54-183-128-52.us-west-1.compute.amazonaws.com/";

    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
