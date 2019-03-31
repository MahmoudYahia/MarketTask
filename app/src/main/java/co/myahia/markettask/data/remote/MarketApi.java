package co.myahia.markettask.data.remote;


import co.myahia.markettask.data.remote.Requst.GetProductsListResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by M.YAHIA on 30/03/2019.
 */
public interface MarketApi {
    @GET(".")
    Observable<GetProductsListResponse> getAllProducts();

}
