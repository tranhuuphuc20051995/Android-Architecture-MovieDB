package com.thphuc.androidarchitecture.module_data.usecases.impl;

import com.google.gson.Gson;

/**
 * Created by hung.nguyendk on 4/29/18.
 */
public class BaseUseCase {
    public String BEARER = "Bearer ";
    private Gson gson;

    public BaseUseCase(Gson gson) {
        this.gson = gson;
    }

    public Gson getGson() {
        return gson;
    }
}
