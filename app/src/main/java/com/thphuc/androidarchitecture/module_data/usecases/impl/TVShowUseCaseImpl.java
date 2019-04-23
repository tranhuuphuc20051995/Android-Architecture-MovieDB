package com.thphuc.androidarchitecture.module_data.usecases.impl;

import com.google.gson.Gson;
import com.thphuc.androidarchitecture.module_data.usecases.TVShowUseCase;
import com.thphuc.androidarchitecture.module_network.service.TVService;

/**
 * Created by TranHuuPhuc on 2019-04-21.
 */
public class TVShowUseCaseImpl extends BaseUseCase implements TVShowUseCase {
    private TVService tvService;

    public TVShowUseCaseImpl(Gson gson, TVService tvService) {
        super(gson);
        this.tvService = tvService;
    }
}
