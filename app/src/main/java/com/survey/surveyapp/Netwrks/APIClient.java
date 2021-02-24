package com.survey.surveyapp.Netwrks;

import android.content.Context;
import android.util.Log;


import com.readystatesoftware.chuck.ChuckInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final String TAG = "APIClient";


    public static Retrofit getClient(Context context) {
        Log.e(TAG, "getClient");
       Retrofit retrofit = null;
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.networkInterceptors().add(httpLoggingInterceptor);
            builder.addInterceptor(new ChuckInterceptor(context));
            retrofit = new Retrofit.Builder()
                    .client(builder.connectTimeout(0, TimeUnit.SECONDS).writeTimeout(0, TimeUnit.SECONDS).readTimeout(0, TimeUnit.SECONDS).build())
                    .baseUrl(WSContants.BASE_MAIN_URL_ANDROID_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }



 /*   public static Retrofit getClientYouTube(Context context) {
        Log.e(TAG, "getClient");
        Retrofit retrofit = null;
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.networkInterceptors().add(httpLoggingInterceptor);
            builder.addInterceptor(new ChuckInterceptor(context));
            retrofit = new Retrofit.Builder()
                    .client(builder.connectTimeout(0, TimeUnit.SECONDS).writeTimeout(0, TimeUnit.SECONDS).readTimeout(0, TimeUnit.SECONDS).build())
                    .baseUrl(WSContants.YOUTUBE_BASE_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }*/


    public static Retrofit getClientAddCard() {
        Log.e(TAG, "getClientAddCard");
        Retrofit retrofit = null;
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

            // Can be Level.BASIC, Level.HEADERS, or Level.BODY
            // See http://square.github.io/okhttp/3.x/logging-interceptor/ to see the options.
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.networkInterceptors().add(httpLoggingInterceptor);
            retrofit = new Retrofit.Builder()
                    .client(builder.connectTimeout(0, TimeUnit.SECONDS).writeTimeout(0, TimeUnit.SECONDS).readTimeout(0, TimeUnit.SECONDS).build())
                    .baseUrl(WSContants.BASE_MAIN_URL_ANDROID)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}




