package com.yelp.fusion.client.connection;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yelp.fusion.client.exception.ErrorHandlingInterceptor;
import com.yelp.fusion.client.models.AccessToken;

import org.junit.Test;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static org.junit.Assert.*;

/**
 * Created by mengzhou on 10/29/17.
 */
public class YelpFusionApiFactoryTest {

    private final static String BASE_URL = "https://api.yelp.com";
    private final static String CONSUMER_ID = "enrCHrPA0nHLjLNXuPuOYw";
    private final static String CONSUMER_SECRET = "VOySH79I4Xlbmz6ufOAxM8baphpvNuah1qw093dZIkc5tiPx96l6ohSzfG4xk327";

    private AccessToken accessToken;

    @Test
    public void getAccessToken() throws Exception {
        System.out.println("test started: ");

        YelpFusionApiFactory apiFactory = new YelpFusionApiFactory();
        apiFactory.getAccessToken(CONSUMER_ID, CONSUMER_SECRET);
        Thread.sleep(10000);
        System.out.println(apiFactory.getAccessToken().getAccessToken());

        /*accessToken();
        Thread.sleep(10000);
        System.out.println(accessToken.getAccessToken());*/

//        Thread.sleep(10000);
    }

    public void accessToken() {
        OkHttpClient authClient = new OkHttpClient.Builder()
                .addInterceptor(new ErrorHandlingInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(getJacksonFactory())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(authClient)
                .build();

        YelpFusionAuthApi client = retrofit.create(YelpFusionAuthApi.class);
        Call<AccessToken> token = client.getToken("client_credentials", CONSUMER_ID, CONSUMER_SECRET);
        token.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                System.out.println("... ... onResponse");
                accessToken = response.body();
                System.out.println(accessToken.getAccessToken());
                System.out.println(accessToken.getTokenType());
                System.out.println(accessToken.getExpiresIn());
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                System.out.println("... ... onFailure");
                try {
                    accessToken = call.execute().body();
                    System.out.println(accessToken == null ? "null" : accessToken.getAccessToken());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static JacksonConverterFactory getJacksonFactory(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return JacksonConverterFactory.create(mapper);
    }

}