package co.myahia.markettask.data.local;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by M.YAHIA on 31/03/2019.
 */
@Module
public class DatabaseModule {
    @Singleton @Provides
    public static LocalDatabase provideMyDatabase(Application context){
        return Room.databaseBuilder(context.getApplicationContext(), LocalDatabase.class, "Products_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton @Provides
    public static ProductDao provideUserDao(LocalDatabase myDatabase){
        return myDatabase.getProductsDao();
    }
}
