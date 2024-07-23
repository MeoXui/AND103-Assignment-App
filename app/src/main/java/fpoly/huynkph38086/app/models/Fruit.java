package fpoly.huynkph38086.app.models;

import com.google.gson.annotations.SerializedName;

public class Fruit {
    @SerializedName("_id")
    public String _id;
    public String name;
    public int price;
    public int quantity;
    public String des;
    public int status;
    public String createdAt;
    public String updatedAt;

    public Fruit() {
    }

    public Fruit(String _id, String name, int price, int quantity, String des, int status, String createdAt, String updatedAt) {
        this._id = _id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.des = des;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
