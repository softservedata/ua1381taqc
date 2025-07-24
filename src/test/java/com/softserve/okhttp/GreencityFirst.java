package com.softserve.okhttp;

import okhttp3.*;

import java.io.IOException;

public class GreencityFirst {

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        //
        String jsonBody = new StringBuilder()
                .append("{")
                .append("\"email\":\"xvr89922@toaik.com\",")
                .append("\"password\":\"Qwerty_1\",")
                .append("\"secretKey\":\"UD~3tDW<$K.rEk$IELFTVQwWU$-tN%IX~q>`NuMpxhUMb$D\"")
                .append("}").toString();
        RequestBody requestBody = RequestBody.create(jsonBody,
                MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url("https://greencity-user.greencity.cx.ua/api/testers/sign-in")
                .addHeader("accept", "*/*")
                //.addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();
        String resultJson = "";
        boolean  isSuccess = false;
        try (Response response = client.newCall(request).execute()) {
            resultJson = response.body().string();
            isSuccess = response.isSuccessful();
        } catch (IOException e) {
            System.out.println("IOException e = " + e);
            throw new RuntimeException(e);
        }
        System.out.println("resultJson = " + resultJson);
        System.out.println("isSuccess = " + isSuccess);
    }
}
