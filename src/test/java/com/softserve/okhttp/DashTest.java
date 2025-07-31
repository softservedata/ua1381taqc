package com.softserve.okhttp;

import com.google.gson.Gson;

import okhttp3.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleEntity {

    private String content;

    public SimpleEntity(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "SimpleEntity [content=" + content + "]";
    }
}

class GreencityLogin {
    private int userId;
    private String accessToken;
    private String refreshToken;
    private String name;
    private boolean ownRegistrations;

    public GreencityLogin(int userId, String accessToken, String refreshToken, String name, boolean ownRegistrations) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.name = name;
        this.ownRegistrations = ownRegistrations;
    }

    public int getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getName() {
        return name;
    }

    public boolean isOwnRegistrations() {
        return ownRegistrations;
    }

    @Override
    public String toString() {
        return "GreencityLogin{" +
                "userId=" + userId +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", name='" + name + '\'' +
                ", ownRegistrations=" + ownRegistrations +
                '}';
    }
}

class GreencityAllEvents {
    private int totalElements;
    private int currentPage;
    private int totalPages;
    private int number;
    private boolean hasPrevious;
    private boolean hasNext;
    private boolean first;
    private boolean last;

    public GreencityAllEvents(int totalElements, int currentPage, int totalPages, int number, boolean hasPrevious, boolean hasNext, boolean first, boolean last) {
        this.totalElements = totalElements;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.number = number;
        this.hasPrevious = hasPrevious;
        this.hasNext = hasNext;
        this.first = first;
        this.last = last;
    }

    @Override
    public String toString() {
        return "GreencityAllEvents{" +
                "totalElements=" + totalElements +
                ", currentPage=" + currentPage +
                ", totalPages=" + totalPages +
                ", number=" + number +
                ", hasPrevious=" + hasPrevious +
                ", hasNext=" + hasNext +
                ", first=" + first +
                ", last=" + last +
                '}';
    }
}

public class DashTest {

    @Test
    public void checkSuccessful() throws Exception {
        Gson gson = new Gson();
        //
        SimpleEntity simpleEntity;
        String token;
        String lifeTime;
        String result;
        //
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody;
        Request request;
        Response response;
        String resultJson;
        //
        // Get TokenLifetime
        request = new Request
                .Builder()
                .url("http://localhost:8080/tokenlifetime")
                .get()
                .build();
        response = client.newCall(request).execute();
        resultJson = response.body().string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        lifeTime = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertEquals(lifeTime, "300000");
        Assertions.assertEquals(response.code(), 200);
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        //
        // Login
        formBody = new FormBody.Builder()
                .add("name", "admin")
                .add("password", "qwerty")
                .build();
        request = new Request.Builder()
                .url("http://localhost:8080/login")
                .post(formBody)
                .build();
        response = client.newCall(request).execute();
        resultJson = response.body().string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        token = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        //
        // Update TokenLifetime
        formBody = new FormBody.Builder()
                .add("token", token)
                .add("time", "901000")
                .build();
        request = new Request.Builder()
                .url("http://localhost:8080/tokenlifetime")
                .put(formBody)
                .build();
        response = client.newCall(request).execute();
        resultJson = response.body().string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        lifeTime = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertEquals(lifeTime, "true");
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        //
        // Get NEW TokenLifetime
        request = new Request.Builder()
                .url("http://localhost:8080/tokenlifetime")
                .get()
                .build();
        response = client.newCall(request).execute();
        resultJson = response.body().string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        lifeTime = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertEquals(lifeTime, "901000");
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        //
        // Logout
        formBody = new FormBody.Builder()
                .add("name", "admin")
                .add("token", token)
                .build();
        request = new Request.Builder()
                .url("http://localhost:8080/logout")
                .post(formBody)
                .build();
        response = client.newCall(request).execute();
        resultJson = response.body().string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        result = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        //
        // Reset Server
        request = new Request.Builder()
                .url("http://localhost:8080/reset")
                .get()
                .build();
        response = client.newCall(request).execute();
        resultJson = response.body().string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        result = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertEquals(result, "true");
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        //
    }

    @Test
    public void checkUser() throws Exception {
        Gson gson = new Gson();
        //
        SimpleEntity simpleEntity;
        String token;
        String lifeTime;
        String result;
        //
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody;
        Request request;
        Response response;
        String resultJson;
        //
        // Login
        formBody = new FormBody.Builder()
                .add("name", "admin")
                .add("password", "qwerty")
                .build();
        request = new Request.Builder()
                .url("http://localhost:8080/login")
                .post(formBody)
                .build();
        response = client.newCall(request).execute();
        resultJson = response.body().string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        token = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        //
        // Get all Users
        HttpUrl.Builder urlBuilder = HttpUrl
                .parse("http://localhost:8080/users")
                .newBuilder();
        urlBuilder.addQueryParameter("token", token);
        String url = urlBuilder.build().toString();
        //
        request = new Request
                .Builder()
                //.url("http://localhost:8080/users?token=" + token)
                .url(url)
                .get()
                .build();
        response = client.newCall(request).execute();
        resultJson = response.body().string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        result = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        System.out.println("result: " + result);
        //
        // Delete users
        formBody = new FormBody.Builder()
                .add("token", token)
                .add("name", "kilinatc")
                .build();
        request = new Request.Builder()
                .url("http://localhost:8080/user?token=" + token + "&name=kilinatc")
                //.url("http://localhost:8080/user")
                //.delete(formBody)
                //.addHeader("Content-Length", "52")
                .delete()
                .build();
        response = client.newCall(request).execute();
        resultJson = response.body().string();
        simpleEntity = gson.fromJson(resultJson, SimpleEntity.class);
        result = simpleEntity.getContent();
        //
        Assertions.assertTrue(response.isSuccessful());
        System.out.println("resultJson: " + resultJson);
        System.out.println("simpleEntity: " + simpleEntity);
        System.out.println("result: " + result);
    }

    @Test
    public void checkGreencity() throws Exception {
        Gson gson = new Gson();
        //
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody;
        Request request;
        Response response;
        GreencityLogin greencityLogin;
        GreencityAllEvents greencityAllEvents;
        String resultJson;
        String token;
        //
        // Login
        String jsonBody =  new StringBuilder()
                .append("{")
                .append("\"email\":\"xvr89922@toaik.com\",")
                .append("\"password\":\"Qwerty_1\",")
                .append("\"secretKey\":\"UD~3tDW<$K.rEk$IELFTVQwWU$-tN%IX~q>`NuMpxhUMb$D\"")
                .append("}").toString();
        //
        requestBody = RequestBody.create(jsonBody,
                MediaType.parse("application/json; charset=utf-8"));
        request = new Request.Builder()
                .url("https://greencity-user.greencity.cx.ua/api/testers/sign-in")
                .addHeader("accept", "*/*")
                //.addHeader("Content-Type", "application/json")
                .post(requestBody)
                .build();
        response = client.newCall(request).execute();
        resultJson = response.body().string();
        greencityLogin = gson.fromJson(resultJson, GreencityLogin.class);
        token = greencityLogin.getAccessToken();
        //
        System.out.println("resultJson = " + resultJson);
        System.out.println("token = " + token);
        System.out.println("greencityLogin = " + greencityLogin);
        //
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertEquals(response.code(), 200);
        //
        // Get all Events
        HttpUrl.Builder urlBuilder = HttpUrl
                .parse("https://greencity.greencity.cx.ua/events")
                .newBuilder();
        urlBuilder.addQueryParameter("page", "0");
        urlBuilder.addQueryParameter("size", "5");
        String url = urlBuilder.build().toString();
        //
        request = new Request
                .Builder()
                .url(url)
                .addHeader("Accept", "*/*")
                .addHeader("Authorization", "Bearer " + token)
                .get()
                .build();
        response = client.newCall(request).execute();
        resultJson = response.body().string();
        greencityAllEvents = gson.fromJson(resultJson, GreencityAllEvents.class);
        //
        Assertions.assertTrue(response.isSuccessful());
        System.out.println("resultJson: " + resultJson);
        System.out.println("greencityAllEvents: " + greencityAllEvents);
        //
    }

    @Test
    public void checkSimple() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request;
        Response response;
        String resultJson;
        //
        // Get
        request = new Request
                .Builder()
                .url("http://localhost:8080/")
                .get()
                .build();
        response = client.newCall(request).execute();
        resultJson = response.body().string();
        //
        Assertions.assertTrue(response.isSuccessful());
        Assertions.assertEquals(response.code(), 200);
        System.out.println("resultJson: " + resultJson);
        //
    }
}             