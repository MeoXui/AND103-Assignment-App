package fpoly.huynkph38086.app.models;

public class Distributor {
    public String _id;
    public String name;
    public String createdAt;
    public String updatedAt;
    public int __v;

    public Distributor() {
    }

    public Distributor(String _id, String name, String createdAt, String updatedAt) {
        this._id = _id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
