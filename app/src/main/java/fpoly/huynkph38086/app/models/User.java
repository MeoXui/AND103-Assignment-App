package fpoly.huynkph38086.app.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("_id")
    public String _id;
    public String username;
    public String password;
    public String email;
    public String name;
    public String avatar;
    public boolean available;
    public String createdAt;
    public String updatedAt;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String _id, String username, String password, String email, String name, String avatar, boolean available, String createdAt, String updatedAt) {
        this._id = _id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.avatar = avatar;
        this.available = available;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
