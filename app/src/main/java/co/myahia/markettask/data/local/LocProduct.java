package co.myahia.markettask.data.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by M.YAHIA on 31/03/2019.
 */

@Entity(tableName = "Products_Table")
public class LocProduct {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "pID")
    private int id_api;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "desc")
    private String desc;

    @ColumnInfo(name = "price")
    private Integer price;

    @ColumnInfo(name = "imgLink")
    private String imgLink;

    @ColumnInfo(name = "imgHeight")
    private String imgHeight;

    @ColumnInfo(name = "imgWidth")
    private String imgWidth;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getId_api() {
        return id_api;
    }

    public void setId_api(int id_api) {
        this.id_api = id_api;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getImgHeight() {
        return imgHeight;
    }

    public void setImgHeight(String imgHeight) {
        this.imgHeight = imgHeight;
    }

    public String getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(String imgWidth) {
        this.imgWidth = imgWidth;
    }
}
