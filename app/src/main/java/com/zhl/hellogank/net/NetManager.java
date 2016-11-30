package com.zhl.hellogank.net;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.zhl.hellogank.common.utils.LogUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.*;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhouhl on 2016/11/1.
 * NetManager
 */

public class NetManager {

    private static RequestApi mRequestApi;

    public static RequestApi getApi() {
        if (mRequestApi == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new StethoInterceptor())
                    .addInterceptor(new HttpLoggingInterceptor(new LogUtils())
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();

            Retrofit mRetrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(RequestApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            mRequestApi = mRetrofit.create(RequestApi.class);
        }

        return mRequestApi;
    }
}
