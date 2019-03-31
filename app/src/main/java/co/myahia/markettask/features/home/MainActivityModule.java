package co.myahia.markettask.features.home;

import co.myahia.markettask.di.PerActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by M.YAHIA on 30/03/2019.
 */
@Module
public abstract class MainActivityModule {
    @Provides
    @PerActivity
    MainFragment provideMainFragment() {
        return new MainFragment();
    }

}
