package com.lilly021.quickok.domain.services.api;

import com.lilly021.quickok.domain.model.Product;
import com.lilly021.quickok.domain.model.Shop;
import com.lilly021.quickok.domain.model.request.ProductRequest;
import com.lilly021.quickok.domain.model.request.ShopRequest;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {

    @POST("api/product/")
    Observable<Product> addProduct(@Body ProductRequest reqeust);

    @GET("api/product/{id}")
    Observable<Product> getProduct(@Path("id") Long id);

    @GET("api/product/search")
    Observable<List<Product>> searchProduct(@Query("term") String term);
}
