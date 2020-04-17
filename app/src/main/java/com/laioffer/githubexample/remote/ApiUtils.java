package com.laioffer.githubexample.remote;

public class ApiUtils {
    public static final String BASE_URL = "http://18.216.222.29/"; // change to result

    public static ApiService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
    }
}
