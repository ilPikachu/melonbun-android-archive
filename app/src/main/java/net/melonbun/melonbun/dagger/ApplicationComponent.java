package net.melonbun.melonbun.dagger;

import net.melonbun.melonbun.dagger.modules.PresenterModule;

import javax.inject.Singleton;
import dagger.Component;

/**
 * ApplicationComponent interface defines the modules that are provided for DI
 */
@Singleton
@Component(modules = {
        PresenterModule.class
})
public interface ApplicationComponent {

    void inject(PresenterModule presenterModule);

}
