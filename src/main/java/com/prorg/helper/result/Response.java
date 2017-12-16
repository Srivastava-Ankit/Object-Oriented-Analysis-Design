package com.prorg.helper.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Response<T> {
    private T result;

    private List<String> errors;

    private Response(T result, List<String> errors) {
        this.result = result;
        this.errors = errors;
    }

    public static Response SuccessEmptyPayload() {
        return Success(new Object());
    }

    public static <T> Response<T> Success(T result) {
        return new Response<>(result, new ArrayList<>());
    }

    public static <T> Response<T> Failure(List<String> errors) {
        return new Response<>(null, errors);
    }

    public static <T> Response<T> Failure(String error) {
        return Failure(Collections.singletonList(error));
    }

    public boolean isSuccessful() {
        return errors.isEmpty();
    }

    public T data() throws Exception {
        if (result == null)
            throw new Exception("Save failed, no serial id");
        return result;
    }

    public List<String> errors() {
        return errors;
    }
}
