package com.example.mryang.yuekaomomi.data.utils;



import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public class OKHttpUtil {

    private static OKHttpUtil okHttpUtil;
    private OkHttpClient client;

    private OKHttpUtil() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                client = new OkHttpClient.Builder().
                addInterceptor(logInterceptor).
                build();
    }

    public static OKHttpUtil getInstance() {
        if (null == okHttpUtil) {
            synchronized (OKHttpUtil.class) {
                if (null == okHttpUtil) {
                    okHttpUtil = new OKHttpUtil();
                }
            }
        }
        return okHttpUtil;
    }

    public void get(String urlString, Callback callback) {
        Request request = new Request.Builder()
                .url(urlString).build();
        client.newCall(request).enqueue(callback);
    }
}
