package com.cokcok.backend.adapter;

import lombok.Getter;

@Getter
public class CommonSuccessResponse {
    private final boolean success;

    public CommonSuccessResponse() {
        this.success = true;
    }
}
