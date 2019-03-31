package net.melonbun.melonbun.dagger.modules;

import net.melonbun.melonbun.common.network.ConnectivityCheck;
import net.melonbun.melonbun.explore.ExplorePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * This is not used right now.
 * PresenterModule class provides object of Presenter classes for DI, used in {@link net.melonbun.melonbun.dagger.ApplicationComponent}
 */
@Module
public class PresenterModule {

    @Provides
    public static ExplorePresenter explorePresenter(ConnectivityCheck connectivityCheck) {
        return new ExplorePresenter(connectivityCheck);
    }
}
