package com.murre.auth.inter;

import com.murre.auth.ApiRequest;

public interface ApiAuthencator {
    void auth(String url);
    void auth(ApiRequest apiRequest);
}