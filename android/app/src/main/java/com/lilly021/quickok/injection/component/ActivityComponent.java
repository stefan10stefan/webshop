package com.lilly021.quickok.injection.component;

import android.content.Context;


import com.lilly021.quickok.domain.services.preferences.PreferenceService;
import com.lilly021.quickok.injection.ActivityContext;
import com.lilly021.quickok.injection.module.ActivityModule;
import com.lilly021.quickok.ui.addManager.AddManagerActivity;
import com.lilly021.quickok.ui.addManager.AddManagerPresenter;
import com.lilly021.quickok.ui.addProduct.AddProductActivity;
import com.lilly021.quickok.ui.addProduct.AddProductPresenter;
import com.lilly021.quickok.ui.addShop.AddShopActivity;
import com.lilly021.quickok.ui.changePassword.ChangePasswordActivity;
import com.lilly021.quickok.ui.login.LoginActivity;
import com.lilly021.quickok.ui.main.MainActivity;
import com.lilly021.quickok.ui.products.ProductsActivity;
import com.lilly021.quickok.ui.registration.RegistrationActivity;
import com.lilly021.quickok.ui.shops.ShopsActivity;
import com.lilly021.quickok.ui.splash.SplashActivity;
import com.lilly021.quickok.ui.splash.SplashPresenter;
import com.lilly021.quickok.ui.viewProduct.ViewProductActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);
    void inject(LoginActivity activity);
    void inject(RegistrationActivity activity);
    void inject(MainActivity activity);
    void inject(ChangePasswordActivity activity);
    void inject(AddShopActivity activity);
    void inject(ShopsActivity activity);
    void inject(ProductsActivity activity);
    void inject(ViewProductActivity activity);
    void inject(AddManagerActivity activity);
    void inject(AddProductActivity activity);
    void inject(SplashPresenter splashPresenter);
    void inject(PreferenceService preferenceService);


    @ActivityContext
    Context context();
}
