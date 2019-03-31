package co.myahia.markettask.app;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import co.myahia.markettask.data.local.DatabaseModule;
import co.myahia.markettask.data.local.LocalDatabase;
import co.myahia.markettask.data.local.ProductDao;
import co.myahia.markettask.viewModel.ViewModelModule;
import dagger.Module;
import dagger.Provides;

/**
 * Created by M.YAHIA on 30/03/2019.
 */
@Module(includes = {ViewModelModule.class})
public abstract class MarketAppModule {

    @Provides
    @Singleton
    static MarketApp provideContext(MarketApp application) {
        return application;
    }




}
