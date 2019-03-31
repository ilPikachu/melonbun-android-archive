package net.melonbun.melonbun;

import android.app.Application;

import net.melonbun.melonbun.dagger.ApplicationComponent;
import net.melonbun.melonbun.dagger.DaggerApplicationComponent;
import net.melonbun.melonbun.dagger.modules.AppModule;

public class MelonbunApplication extends Application {
    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        // Called only once at the start of the application to setup DI applicationComponent single static instance used through out the app
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
