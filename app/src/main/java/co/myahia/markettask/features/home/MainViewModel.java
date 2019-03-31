package co.myahia.markettask.features.home;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import co.myahia.markettask.data.local.LocProduct;
import co.myahia.markettask.data.manager.DataManager;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by M.YAHIA on 30/03/2019.
 */
public class MainViewModel extends AndroidViewModel {


    private DataManager mDataManager;
    private CompositeDisposable mDisposable;

    @Inject
    public MainViewModel(@NonNull Application application, DataManager manager) {
        super(application);
        this.mDataManager = manager;
        mDisposable = new CompositeDisposable();
    }

    public LiveData<List<LocProduct>> getAllProducts() {
        return mDataManager.getProductsList(getApplication().getBaseContext());
    }

    public LiveData<Boolean> getInternetState() {
        return mDataManager.getInternetState();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mDisposable != null) {
            mDisposable.clear();
            mDisposable = null;
        }
    }
}
