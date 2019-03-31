package co.myahia.markettask.di;

import co.myahia.markettask.features.home.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by M.YAHIA on 30/03/2019.
 */
@Module
public abstract class ActivityBindingModule {
    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity mainActivity();
}
