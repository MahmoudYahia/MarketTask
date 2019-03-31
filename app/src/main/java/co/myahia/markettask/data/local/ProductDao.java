package co.myahia.markettask.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by M.YAHIA on 31/03/2019.
 */
@Dao
public interface ProductDao {

    @Query("SELECT * FROM products_table")
    List<LocProduct> getProductList();

    @Insert
    void insertAll(List<LocProduct> products);

    @Query("DELETE FROM products_table")
    void deleteAllProducts();
}
