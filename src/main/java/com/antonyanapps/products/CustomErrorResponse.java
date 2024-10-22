package com.antonyanapps.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

public class CustomErrorResponse {
    private String message;
    private String error;
    private int status;

    public CustomErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
        this.error = status.getReasonPhrase();
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public int getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
