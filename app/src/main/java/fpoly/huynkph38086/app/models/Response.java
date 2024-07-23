package fpoly.huynkph38086.app.models;

public class Response<T> {
    public int status;
    public String mess;
    public T data;

    public Response() {
    }

    public Response(int status, String mess, T data) {
        this.status = status;
        this.mess = mess;
        this.data = data;
    }
}
