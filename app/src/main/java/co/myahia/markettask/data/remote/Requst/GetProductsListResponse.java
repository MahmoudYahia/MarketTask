package co.myahia.markettask.data.remote.Requst;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import co.myahia.markettask.data.remote.model.ApiProduct;

/**
 * Created by M.YAHIA on 27/03/2019.
 */
public class GetProductsListResponse {

    @SerializedName("data")
    @Expose
    private List<ApiProduct> data = null;
    @SerializedName("count")
    @Expose
    private Integer count;

    public List<ApiProduct> getData() {
        return data;
    }

    public void setData(List<ApiProduct> data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
