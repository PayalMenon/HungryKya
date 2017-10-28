package com.yelp.fusion.client.connection;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.yelp.fusion.client.connection.interceptors.AccessTokenInterceptor;
import com.yelp.fusion.client.exception.ErrorHandlingInterceptor;
import com.yelp.fusion.client.models.AccessToken;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Ranga on 2/22/2017.
 */

public class YelpFusionApiFactory {
    private static final String YELP_API_BASE_URL = "https://api.yelp.com";

    private AccessToken accessToken;

    public YelpFusionApiFactory() {}

    public YelpFusionApi createAPI(String clientId, String clientSecret) throws IOException {
        getAccessToken(clientId, clientSecret);
        long timeout = 5000;
        while (accessToken == null && timeout > 0) {
            try {
                Thread.sleep(500);
                timeout -= 500;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return getYelpFusionApi();
    }

    public YelpFusionApi createAPI(String accessToken) throws IOException {
        this.accessToken = new AccessToken();
        this.accessToken.setAccessToken(accessToken);
        this.accessToken.setTokenType("Bearer");
        return getYelpFusionApi();
    }

    private YelpFusionApi getYelpFusionApi() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new AccessTokenInterceptor(accessToken))
                .addInterceptor(new ErrorHandlingInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getAPIBaseUrl())
                .addConverterFactory(getJacksonFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();
        return retrofit.create(YelpFusionApi.class);
    }

    public void getAccessToken(String clientId, String clientSecret) throws IOException {
        OkHttpClient authClient = new OkHttpClient.Builder()
                .addInterceptor(new ErrorHandlingInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getAPIBaseUrl())
                .addConverterFactory(getJacksonFactory())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(authClient)
                .build();

        YelpFusionAuthApi client = retrofit.create(YelpFusionAuthApi.class);
        Call<AccessToken> token = client.getToken("client_credentials", clientId, clientSecret);
        token.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                accessToken = response.body();
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                try {
                    accessToken = call.execute().body();
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

    public String getAPIBaseUrl() {
        return YELP_API_BASE_URL;
    }

}

