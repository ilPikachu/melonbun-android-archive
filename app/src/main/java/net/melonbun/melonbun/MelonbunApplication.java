package net.melonbun.melonbun;

import android.app.Application;

import net.melonbun.melonbun.dagger.ApplicationComponent;
import net.melonbun.melonbun.dagger.DaggerApplicationComponent;


public class MelonbunApplication extends Application {
    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        // Called only once at the start of the application to setup DI applicationComponent single static instance used through out the app
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.create();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
