package fpoly.huynkph38086.app.services;

public class Response<T> {
    public int status;
    public String mess;
    public T data;
    public String token, refreshToken;

    public Response() {
    }

    public Response(int status, String mess, T data) {
        this.status = status;
        this.mess = mess;
        this.data = data;
    }

    public Response(int status, String mess, T data, String token, String refreshToken) {
        this.status = status;
        this.mess = mess;
        this.data = data;
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
