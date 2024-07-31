package fpoly.huynkph38086.app.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Fruit {
    @SerializedName("_id")
    public String _id;
    public String name;
    public int quantity;
    public int price;
    public int status;
    public ArrayList<String> images;
    public String des;
    @SerializedName("id_distributor")
    public Distributor distributor;
    public String createdAt;
    public String updatedAt;

    public Fruit() {
    }

    public Fruit(String name, int quantity, int price, int status, ArrayList<String> images, String des, Distributor distributor) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.images = images;
        this.des = des;
        this.distributor = distributor;
    }

    public Fruit(String _id, String name, int quantity, int price, int status, ArrayList<String> images, String des, Distributor distributor, String createdAt, String updatedAt) {
        this._id = _id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.images = images;
        this.des = des;
        this.distributor = distributor;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
