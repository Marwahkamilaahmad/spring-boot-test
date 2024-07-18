package com.example.crud_spring_boot.web.controllers;


import java.util.ArrayList;
import java.util.List;

public class ResponseData<T> {

    private boolean status;
    private List<String> message = new ArrayList<>();
    private T payLoad;

    // Getters and Setters
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public T getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(T payLoad) {
        this.payLoad = payLoad;
    }
}