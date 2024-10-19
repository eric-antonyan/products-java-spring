package com.antonyanapps.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
public class CustomErrorResponse {
    private String message;
    private String error;
    private int status;

    public CustomErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
        this.error = status.getReasonPhrase();
    }
}
