package co.myahia.markettask.data.manager;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import co.myahia.markettask.data.local.LocProduct;
import co.myahia.markettask.data.local.LocalDatabase;
import co.myahia.markettask.data.local.ProductDao;
import co.myahia.markettask.data.remote.MarketApi;
import co.myahia.markettask.data.remote.model.ApiProduct;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by M.YAHIA on 30/03/2019.
 */
public class DataManager {

    //Context context;
    private MarketApi mMarketApi;
    private CompositeDisposable mDisposable;
    private final MutableLiveData<List<LocProduct>> productListObservable;
    private final MutableLiveData<Boolean> isInternetAccess;
    private ProductDao mProductDao;

    @Inject
    public DataManager(MarketApi marketApi, LocalDatabase dao) {
        this.mMarketApi = marketApi;
        mDisposable = new CompositeDisposable();
        productListObservable = new MutableLiveData<>();
        isInternetAccess = new MutableLiveData<>();
        this.mProductDao = dao.getProductsDao();
    }

    public LiveData<List<LocProduct>> getProductsList(Context context) {
        getProducts(context);

        return productListObservable;
    }

    private void getProducts(Context context) {
        if (isInternetOn(context)) {
            Log.i("tstDataSrc", String.valueOf("On -APi"));
            isInternetAccess.setValue(true);
            getProductsFromApi();
        } else {
            Log.i("tstDataSrc", String.valueOf("Off -loc"));
            isInternetAccess.setValue(false);
            getLocalProducts();
        }
    }

    public LiveData<Boolean> getInternetState() {
        return isInternetAccess;
    }

    private void getProductsFromApi() {
        mDisposable.add(mMarketApi.getAllProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> transformDataTypeFromApiToLocal(response.getData()))
                .doOnNext(this::saveProductsInDB)
                .subscribe(data -> {
                    if (data != null) {
                        productListObservable.setValue(data);
                    }
                }));

    }

    private void getLocalProducts() {
        mDisposable.add(Observable.just(mProductDao)
                .subscribeOn(Schedulers.io())
                .map(ProductDao::getProductList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productListObservable::setValue, throwable -> {
                    Log.i("tstTh", throwable.toString());
                }));
    }

    private List<LocProduct> transformDataTypeFromApiToLocal(List<ApiProduct> list) {
        List<LocProduct> locProductList = new ArrayList<>();
        for (ApiProduct apiProduct : list) {

            LocProduct locProduct = new LocProduct();
            locProduct.setId_api(apiProduct.getId());
            locProduct.setTitle(apiProduct.getName());
            locProduct.setPrice(apiProduct.getPrice());
            locProduct.setDesc(apiProduct.getProductDescription());
            locProduct.setImgLink(apiProduct.getImage().getLink());
            locProduct.setImgWidth(apiProduct.getImage().getWidth());
            locProduct.setImgHeight(apiProduct.getImage().getHeight());

            locProductList.add(locProduct);
        }
        return locProductList;
    }

    private void saveProductsInDB(List<LocProduct> locProducts) {
        mDisposable.add(Observable.just(locProducts)
                .subscribeOn(Schedulers.io())
                .doOnNext(products -> mProductDao.deleteAllProducts())
                .doOnNext(products -> mProductDao.insertAll(products))
                .subscribe());
    }

    private boolean isInternetOn(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}
