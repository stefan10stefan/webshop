package com.lilly021.quickok.domain.services.api;

import com.lilly021.quickok.domain.services.preferences.PreferenceService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    private final PreferenceService preferenceService;

    @Inject
    public RequestInterceptor(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Request original = chain.request();
        Request resultRequest = original;

        String token = preferenceService.getToke();

        if(token != null && !token.equals("") ) {
            resultRequest = original.newBuilder()
                .header("Authorization", "Bearer " + preferenceService.getToke())
                .method(original.method(), original.body())
                .build();
        }

        return chain.proceed(resultRequest);
    }
}
