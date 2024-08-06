package fpoly.huynkph38086.app.hamdle;

public interface ItemHandle<T> {
    public void delete(String id);
    public void update(T old);
}
