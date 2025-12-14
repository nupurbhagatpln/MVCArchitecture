package com.mvcModel.MvcModel.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    @JsonFormat(pattern = "hh-mm-ss dd-MM-yyyy")
    private LocalDateTime timeStamp;

    private T data;

    private ApiError error;

    public ApiResponse() {
        this.timeStamp=LocalDateTime.now();
    }

    public ApiResponse(ApiError error) {
        this(); // call the default constructor to add the time stamp
        this.error = error;
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }
}
