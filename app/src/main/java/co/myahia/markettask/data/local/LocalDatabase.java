package co.myahia.markettask.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import co.myahia.markettask.app.MarketApp;
import dagger.Binds;

/**
 * Created by M.YAHIA on 31/03/2019.
 */

@Database(entities = {LocProduct.class}, version = 1, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {
   // private static LocalDatabase localDatabaseInstance;

    public abstract ProductDao getProductsDao();

//    public static LocalDatabase getInstance(MarketApp app) {
//        if (localDatabaseInstance != null)
//            return localDatabaseInstance;
//
//        localDatabaseInstance = getDatabase(app.getApplicationContext());
//        return localDatabaseInstance;
//    }
//
//
//    private static LocalDatabase getDatabase(Context context) {
//        return Room.databaseBuilder(context.getApplicationContext(), LocalDatabase.class, "Products_database")
//                .fallbackToDestructiveMigration()
//                .build();
//    }
}
