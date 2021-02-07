package com.lilly021.quickok.domain.services.api;

import com.lilly021.quickok.domain.model.Cart;
import com.lilly021.quickok.domain.model.Product;
import com.lilly021.quickok.domain.model.request.ProductRequest;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartService {

    @POST("api/cart/{productId}")
    Observable<Cart> addProductCart(@Path("productId") Long productId);

    @GET("api/cart/")
    Observable<Cart> getCart();

    @PUT("api/cart/buy")
    Observable<Cart> buy();
}
