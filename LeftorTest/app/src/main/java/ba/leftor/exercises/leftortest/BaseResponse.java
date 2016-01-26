package ba.leftor.exercises.leftortest;

/**
 * Created by jasminsuljic on 26/01/16.
 */
public class BaseResponse<T> {
    private T data;

    public BaseResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
