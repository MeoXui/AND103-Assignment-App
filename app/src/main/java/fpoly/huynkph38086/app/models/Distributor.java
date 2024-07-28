package fpoly.huynkph38086.app.models;

import com.google.gson.annotations.SerializedName;

public class Distributor {
    @SerializedName("_id")
    public String _id;
    public String name;
    public String createdAt;
    public String updatedAt;

    public Distributor() {
    }

    public Distributor(String name) {
        this.name = name;
    }

    public Distributor(String _id, String name, String createdAt, String updatedAt) {
        this._id = _id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
