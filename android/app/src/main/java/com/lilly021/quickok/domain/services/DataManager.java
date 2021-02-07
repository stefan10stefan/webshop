package com.lilly021.quickok.domain.services;

import com.google.gson.Gson;
import com.lilly021.quickok.domain.model.AuthResponse;
import com.lilly021.quickok.domain.model.Cart;
import com.lilly021.quickok.domain.model.Message;
import com.lilly021.quickok.domain.model.Product;
import com.lilly021.quickok.domain.model.Shop;
import com.lilly021.quickok.domain.model.User;
import com.lilly021.quickok.domain.model.request.ChangePasswordRequest;
import com.lilly021.quickok.domain.model.request.EditProfileRequest;
import com.lilly021.quickok.domain.model.request.LoginRequest;
import com.lilly021.quickok.domain.model.request.ProductRequest;
import com.lilly021.quickok.domain.model.request.RegistrationRequest;
import com.lilly021.quickok.domain.model.request.ShopRequest;
import com.lilly021.quickok.domain.services.api.ApiServiceManager;
import com.lilly021.quickok.domain.services.preferences.PreferenceService;
import com.lilly021.quickok.events.BaseEvent;
import com.lilly021.quickok.settings.AppSettings;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@Singleton
public class DataManager {

    private final PreferenceService preferenceService;
    private final ApiServiceManager apiServiceManager;

    @Inject
    public DataManager(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
        apiServiceManager = new ApiServiceManager(preferenceService);
    }

    public boolean isUserLogin() {
        return preferenceService.isUserLogin();
    }

    // Login

    public Observable<AuthResponse> login(String username, String password) {

        return apiServiceManager.getUserService().login(
                username, password, AppSettings.CLIENT_ID, AppSettings.CLIENT_SECRET,
                        AppSettings.GRANT_TYPE_PASSWORD
        );
    }

    public Observable<User> getCurrentUser() {
        return apiServiceManager.getUserService().getCurrentUser();
    }

    public void setCurrentUser(User user) {

        preferenceService.setUser(user);
    }

    public User getUser() {
        return preferenceService.getUser();
    }

    public void setToken(String token) {

        preferenceService.setToken(token);
        preferenceService.setUserLogin(true);
    }

    // Registration

    public Observable<User> registration(String email, String password, String firstName, String lastName) {

        return apiServiceManager.getUserService().addUser(new RegistrationRequest(email, password,
                firstName, lastName));
    }

    public Observable<User> addUserManager(String email, String password, String firstName, String lastName) {

        return apiServiceManager.getUserService().addUserManager(new RegistrationRequest(email, password,
                firstName, lastName));
    }

    // Edit profile

    public Observable<User> editProfile(Long id, String firstName, String lastName) {

        return apiServiceManager.getUserService().editProfile(new EditProfileRequest(id,
                firstName, lastName));
    }


    // Logout

    public void logout() {
        preferenceService.setToken("");
        preferenceService.setUserLogin(false);
    }

    // Change Password

    public Observable<User> changePassword(String password) {

        return apiServiceManager.getUserService().changePassword(new ChangePasswordRequest(password));
    }

    // SHOP

    public Observable<Shop> addShop(String name, double lat, double lng) {

        ShopRequest request = new ShopRequest();
        request.name = name;
        request.lat = lat;
        request.lng = lng;

        return apiServiceManager.getShopService().addShop(request);
    }

    public Observable<List<Shop>> searchShop(String term) {
        return apiServiceManager.getShopService().searchShop(term);
    }

    // PRODUCT

    public Observable<Product> addProduct(String name, String description, double price, String image, Long shopId) {

        ProductRequest request = new ProductRequest();
        request.name = name;
        request.description = description;
        request.price = price;
        request.shopId = shopId;
        request.image = image;

        return apiServiceManager.getProductService().addProduct(request);
    }

    public Observable<List<Product>> searchProduct(String term) {
        return apiServiceManager.getProductService().searchProduct(term);
    }

    public Observable<Product> getProduct(Long productId) {


        return apiServiceManager.getProductService().getProduct(productId);
    }

    public Observable<Cart> addProductCart(Long productId) {


        return apiServiceManager.getCartService().addProductCart(productId);
    }

    public Observable<Cart> getCart() {

        return apiServiceManager.getCartService().getCart();
    }

    public Observable<Cart> buy() {

        return apiServiceManager.getCartService().buy();
    }

    public Observable<List<Message>> getMessages() {
        return apiServiceManager.getMessageService().getMessages();
    }

 }
