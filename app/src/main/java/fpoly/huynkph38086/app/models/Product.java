package fpoly.huynkph38086.app.models;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("_id")
    public String _id;
    public String name;
    public int price;
//    public int quantity;
//    public String des;
    public int status;
    public String createdAt;
    public String updatedAt;

    public Product(String _id, String name, int price, int status, String createdAt, String updatedAt) {
        this._id = _id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
