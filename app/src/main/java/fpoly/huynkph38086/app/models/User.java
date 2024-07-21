package fpoly.huynkph38086.app.models;

public class User {
    public String _id;
    public String username;
    public String password;
    public String email;
    public String avatar;
    public String createdAt;
    public String updatedAt;

    public User(String _id, String username, String password, String email, String avatar, String createdAt, String updatedAt) {
        this._id = _id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
