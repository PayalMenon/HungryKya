package com.yelp.fusion.client.connection;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yelp.fusion.client.connection.interceptors.AccessTokenInterceptor;
import com.yelp.fusion.client.exception.ErrorHandlingInterceptor;
import com.yelp.fusion.client.models.AccessToken;

import java.io.IOException;

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
    private static final String ACCESS_TOKEN = "WWYsE6DRjccMqkgsnF_hfv-z7r5ZJAuvAtWti_RuK2Q-fE64WODM9CYTuZuyP89dHEI_ee8tbnzjtSuGeHW0zmOzAy_YDT7I95Kwpm5Q9_I8La6Cds37kcTVfCbxWXYx";

    private AccessToken accessToken;

    public YelpFusionApiFactory() {}

    public YelpFusionApi createAPI(String clientId, String clientSecret) throws IOException {
        if(accessToken == null) getAccessToken(clientId, clientSecret);
        /*long timeout = 10000;
        while (accessToken == null && timeout > 0) {
            try {
                Thread.sleep(500);
                timeout -= 500;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        if(accessToken == null) return createAPI();
        return getYelpFusionApi();
    }

    public YelpFusionApi createAPI() throws IOException {
        this.accessToken = new AccessToken();
        this.accessToken.setAccessToken(ACCESS_TOKEN);
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

    public AccessToken getAccessToken() {
        return accessToken;
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

