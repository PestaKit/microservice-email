package io.pestakit.email.api.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

/**
 * Used to send errors to the user
 * @author Tano Iannetta
 */
public class ApiError {

    private HttpStatus status;
    private String error;
    private List<String> messages;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.error = message;
        this.messages = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.error = message;
        messages = Arrays.asList(error);
    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
