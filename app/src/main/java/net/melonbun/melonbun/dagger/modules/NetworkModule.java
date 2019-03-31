package net.melonbun.melonbun.dagger.modules;

import net.melonbun.melonbun.MelonbunApplication;
import net.melonbun.melonbun.common.network.ConnectivityCheck;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    public static ConnectivityCheck provideConnectivityCheck(MelonbunApplication application) {
        return new ConnectivityCheck(application);
    }
}
