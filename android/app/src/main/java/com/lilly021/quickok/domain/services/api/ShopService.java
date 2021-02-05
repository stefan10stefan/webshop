package com.lilly021.quickok.domain.services.api;

import com.lilly021.quickok.domain.model.Shop;
import com.lilly021.quickok.domain.model.User;
import com.lilly021.quickok.domain.model.request.RegistrationRequest;
import com.lilly021.quickok.domain.model.request.ShopRequest;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Query;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ShopService {

    @POST("api/shop/")
    Observable<Shop> addShop(@Body ShopRequest reqeust);

    @GET("api/shop/search")
    Observable<List<Shop>> searchShop(@Query("term") String term);
}
