package co.myahia.markettask.app;

import android.app.Application;

import javax.inject.Singleton;

import co.myahia.markettask.data.local.DatabaseModule;
import co.myahia.markettask.data.remote.NetworkModule;
import co.myahia.markettask.di.ActivityBindingModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

/**
 * Created by M.YAHIA on 30/03/2019.
 */
@Singleton
@Component(modules = { MarketAppModule.class, AndroidSupportInjectionModule.class,DatabaseModule.class, ActivityBindingModule.class, NetworkModule.class})
public interface MarketAppComponent extends AndroidInjector<DaggerApplication> {
    void inject(MarketApp application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        MarketAppComponent build();
    }




}
