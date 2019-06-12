package net.melonbun.melonbun.common.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {

    private final Retrofit.Builder retrofitBuilder;

    private OkHttpClient okHttpClient;

    public ApiBuilder(String baseUrl, OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        retrofitBuilder = new Retrofit.Builder().baseUrl(baseUrl);
    }

    public ApiBuilder useGsonSerializer() {
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        return this;
    }

    public ApiBuilder useRxJava2() {
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return this;
    }

    public <T> T build(Class<T> apiClass) {
        return retrofitBuilder.client(okHttpClient)
                .build().create(apiClass);
    }
}
