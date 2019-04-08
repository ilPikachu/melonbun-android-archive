package net.melonbun.melonbun.dagger.modules;

import android.app.Application;

import net.melonbun.melonbun.MelonbunApplication;
import net.melonbun.melonbun.R;
import net.melonbun.melonbun.common.network.ApiBuilder;
import net.melonbun.melonbun.common.network.ConnectivityCheck;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    @Named("melonbunApiUrl")
    public static String provideMelonbunApiUrl(MelonbunApplication application) {
        return application.getString(R.string.url_melonbun_api);
    }

    @Singleton
    @Provides
    public static OkHttpClient provideOkHttpClient(){
        return new OkHttpClient().newBuilder().build();
    }

    @Singleton
    @Provides
    public static ApiBuilder provideApiBuilder(@Named("melonbunApiUrl") String baseUrl, OkHttpClient okHttpClient) {
        return new ApiBuilder(baseUrl, okHttpClient);
    }

    @Singleton
    @Provides
    public static ConnectivityCheck provideConnectivityCheck(MelonbunApplication application) {
        return new ConnectivityCheck(application);
    }
}
