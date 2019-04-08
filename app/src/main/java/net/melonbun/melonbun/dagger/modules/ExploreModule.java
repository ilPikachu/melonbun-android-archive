package net.melonbun.melonbun.dagger.modules;

import net.melonbun.melonbun.common.network.ApiBuilder;
import net.melonbun.melonbun.explore.network.ExploreApi;
import net.melonbun.melonbun.explore.network.ExploreService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ExploreModule {

    @Singleton
    @Provides
    public static ExploreApi provideExploreApi(ApiBuilder apiBuilder) {
        return new ExploreApi.Builder(apiBuilder).build();
    }

    @Singleton
    @Provides
    public static ExploreService provideExploreService(ExploreApi exploreApi) {
        return new ExploreService(exploreApi);
    }
}
