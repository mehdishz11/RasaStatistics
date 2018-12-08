package com.rasa.statistics.utils;

import com.rasa.statistics.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebUtils {
    private static final String BASE_URL="http://statistics.harasa.co.ir/api/";

    private static Retrofit retrofit;


    public static Retrofit getRetrofit(){
        if(retrofit==null){
            final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(50, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    ;


            if(BuildConfig.DEBUG){
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                okHttpClientBuilder.addInterceptor(loggingInterceptor);
            }

            final OkHttpClient okHttpClient=okHttpClientBuilder.build();

            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();
            }
        }

        return retrofit;
    }

}
