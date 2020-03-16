package com.ibsvalleyn.outlet.api;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.ibsvalleyn.outlet.activities.MyApplication;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.content.Context.MODE_PRIVATE;
import static com.ibsvalleyn.outlet.helper.Static_variables.API_LINK;
import static com.ibsvalleyn.outlet.helper.Static_variables.API_LINK3;

public class RetrofitClient {
    public static volatile RetrofitClient retrofitClient;
    private static final String TAG = "ServiceGenerator";
    private static final String HEADER_CACHE_CONTROL = "Cache-Control";
    private static final String HEADER_PRAGMA = "Pragma";
    private static Api service = null;
    static String NEW_BASE_URL;

    public static Api getInstance(Context context) {
        lang(context);
        if (retrofitClient == null) {
            synchronized (RetrofitClient.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .callFactory(okHttpClient())
                       .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl(NEW_BASE_URL)
                        .build();
                Log.e("TAG", "fetcher Before");

                service = retrofit.create(Api.class);
            }
        }

        Log.e("TAG", "fetcher After");

        return service;
    }

    public static void lang(Context context) {
        SharedPreferences pref = context.getSharedPreferences("lang", MODE_PRIVATE);
        SharedPreferences.Editor prefEdit = context.getSharedPreferences("lang", MODE_PRIVATE).edit();
        String language = pref.getString("language", "en");
        if (language.equals("en")) {
            prefEdit.putString("language", "en");
            NEW_BASE_URL = API_LINK + "en-US/api/";
            prefEdit.apply();
            Log.e("tag", "NEW_BASE_URL " + NEW_BASE_URL);

        } else {
            NEW_BASE_URL = API_LINK + "ar-SA/api/";
            prefEdit.putString("language", "ar");
            prefEdit.apply();
            Log.e("tag", "NEW_BASE_URL " + NEW_BASE_URL);
        }
    }

    private static final long cacheSize = 70 * 1024 * 1024; // 5 MB

    private static OkHttpClient okHttpClient() {

        return new OkHttpClient.Builder()
                .cache(cache())
                .addInterceptor(httpLoggingInterceptor()) // used if network off OR on
                .addNetworkInterceptor(networkInterceptor()) // only used when network is on
                .addInterceptor(offlineInterceptor())
                .build();

    }

    private static Cache cache() {
        return new Cache(new File(MyApplication.getInstance().getCacheDir(), "someIdentifier"), cacheSize);
    }

    private static Interceptor offlineInterceptor() {
        return chain -> {
            Log.d(TAG, "offline interceptor: called.");
            Request request = chain.request();

            // prevent caching when network is on. For that we use the "networkInterceptor"
            if (!MyApplication.hasNetwork()) {
                CacheControl cacheControl = new CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build();

                request = request.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build();
            }

            return chain.proceed(request);
        };
    }

    private static Interceptor networkInterceptor() {
        return chain -> {
            Log.d(TAG, "network interceptor: called.");

            Response response = chain.proceed(chain.request());

            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(0, TimeUnit.SECONDS)
                    .build();

            return response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build();

        };
    }

    private static HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(message -> Log.d(TAG, "log: http log: " + message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }


}