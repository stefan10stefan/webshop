package com.lilly021.quickok.domain.services.api;

import com.lilly021.quickok.domain.services.preferences.PreferenceService;
import com.lilly021.quickok.settings.AppSettings;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import hu.akarnokd.rxjava3.retrofit.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceManager {

    private final Retrofit retrofit;
    private UserService userService;
    private ShopService shopService;
    private ProductService productService;
    private CartService cartService;
    private MessageService messageService;
    private PreferenceService preferenceService;

    @Inject
    public ApiServiceManager(PreferenceService preferenceService)
    {
        this.preferenceService = preferenceService;
        retrofit = createRetrofit();
    }

    public MessageService getMessageService() {

        if(messageService == null) {
            messageService = retrofit.create(MessageService.class);
        }

        return messageService;
    }

    public UserService getUserService() {

        if(userService == null) {
            userService = retrofit.create(UserService.class);
        }

        return userService;
    }

    public CartService getCartService() {

        if(cartService == null) {
            cartService = retrofit.create(CartService.class);
        }

        return cartService;
    }

    public ProductService getProductService() {

        if(productService == null) {
            productService = retrofit.create(ProductService.class);
        }

        return productService;
    }

    public ShopService getShopService() {

        if(shopService == null) {
            shopService = retrofit.create(ShopService.class);
        }

        return shopService;
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(AppSettings.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build();
    }

    private OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new RequestInterceptor(preferenceService));
        return httpClient.build();
    }
}
