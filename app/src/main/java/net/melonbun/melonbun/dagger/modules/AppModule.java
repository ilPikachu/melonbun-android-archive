package net.melonbun.melonbun.dagger.modules;

import net.melonbun.melonbun.MelonbunApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final MelonbunApplication application;

    public AppModule(MelonbunApplication application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public MelonbunApplication application() {
        return application;
    }
}
