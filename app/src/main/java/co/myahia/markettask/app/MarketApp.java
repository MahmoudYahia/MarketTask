package co.myahia.markettask.app;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by M.YAHIA on 30/03/2019.
 */

public class MarketApp extends DaggerApplication  {

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        MarketAppComponent component = DaggerMarketAppComponent.builder().application(this).build();
        component.inject(this);
        return component;
    }

}
