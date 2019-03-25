package net.melonbun.melonbun.dagger.modules;

import net.melonbun.melonbun.explore.ExplorePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * PresenterModule class provides object of Presenter classes for DI, used in {@link net.melonbun.melonbun.dagger.ApplicationComponent}
 */
@Module
public class PresenterModule {

    @Provides
    public ExplorePresenter explorePresenter(){
        return new ExplorePresenter();
    }
}
