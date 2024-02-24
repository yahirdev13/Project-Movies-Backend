package mx.com.utez.projectmoviesbackend.utils;

import lombok.Data;

@Data
public class CustomResponse<T> {
    private T data;
    private String message;
    private boolean error;
    private int code;

    public CustomResponse() {
        this.data = null;
        this.message = null;
        this.error = false;
        this.code = 0;
    }
}
