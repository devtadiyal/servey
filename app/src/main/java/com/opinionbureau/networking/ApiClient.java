package com.opinionbureau.networking;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by day on 07/04/19.
 */

public class ApiClient {
    public static String BASE_URL = "http://app.opinionbureau.com/";//
    public static final String IMAGE_BASE_URL = "https://timeteck.projectdevelopment.co/uploads/profile_images/";
    private static Retrofit retrofit;
    private static ApiClient mInstance = null;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    private ApiClient(Context context) {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        //Add gson to retrofit
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        // Add Ok http client to retrofit
      /*     // Add Ok http client to retrofit
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();
            requestBuilder.addHeader("token", TextUtils.isEmpty( Preference.getInstance().getFromPreference("token"))?"": Preference.getInstance().getFromPreference("token"))
            return chain.proceed(requestBuilder.build());
        });

        OkHttpClient mOkHttpClient = builder.build();*/

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
    }

    public static ApiClient getInstance(Context context) {
        if (mInstance == null) {
            return mInstance = new ApiClient(context);
        }

        return mInstance;
    }

    public static Retrofit getClient() {
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);
        if (retrofit == null) {

            httpClient.readTimeout(1000, TimeUnit.SECONDS)
                    .connectTimeout(1000, TimeUnit.SECONDS);


            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();
        }

        return retrofit;
    }

    public <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}