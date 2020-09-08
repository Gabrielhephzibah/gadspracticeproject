package com.cherish.gadspracticeproject.data.remote;

public class APIUtils {
    private APIUtils() {}

    public static final String BASE_URL = "https://gadsapi.herokuapp.com/";


    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }


}
