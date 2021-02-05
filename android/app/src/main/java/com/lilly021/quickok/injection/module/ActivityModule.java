package com.lilly021.quickok.injection.module;

import android.content.Context;
import android.app.Activity;

import com.lilly021.quickok.domain.services.DataManager;
import com.lilly021.quickok.domain.services.api.ApiServiceManager;
import com.lilly021.quickok.domain.services.preferences.PreferenceService;
import com.lilly021.quickok.injection.ActivityContext;
import com.lilly021.quickok.ui.addManager.AddManagerPresenter;
import com.lilly021.quickok.ui.addProduct.AddProductPresenter;
import com.lilly021.quickok.ui.addShop.AddShopPresenter;
import com.lilly021.quickok.ui.changePassword.ChangePasswordPresenter;
import com.lilly021.quickok.ui.login.LoginPresenter;
import com.lilly021.quickok.ui.main.MainPresenter;
import com.lilly021.quickok.ui.products.ProductsPresenter;
import com.lilly021.quickok.ui.registration.RegistrationPresenter;
import com.lilly021.quickok.ui.shops.ShopsPresenter;
import com.lilly021.quickok.ui.splash.SplashPresenter;
import com.lilly021.quickok.ui.viewProduct.ViewProductPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    protected final Activity activity;
    protected final DataManager dataManager;
    protected final PreferenceService preferenceService;
    protected final ApiServiceManager apiServiceManager;

    public ActivityModule(Activity activity) {
        this.activity = activity;
        preferenceService = new PreferenceService(activity);
        dataManager = new DataManager(preferenceService);
        apiServiceManager = new ApiServiceManager(preferenceService);
    }

    @Provides
    public Activity providesActivity() { return activity; }

    @Provides
    public DataManager providesDataManager() { return dataManager; }

    @Provides
    public PreferenceService providesPreferenceService() { return preferenceService; }

    @Provides
    public SplashPresenter providesSplashPresenter() { return new SplashPresenter(dataManager); }

    @Provides
    public LoginPresenter providesLoginPresenter() { return new LoginPresenter(dataManager); }

    @Provides
    public RegistrationPresenter providesRegistrationPresenter() { return new RegistrationPresenter(dataManager); }

    @Provides
    public ChangePasswordPresenter providesChangePasswordPresenter() { return new ChangePasswordPresenter(dataManager); }

    @Provides
    public AddShopPresenter providesAddShopPresenter() { return new AddShopPresenter(dataManager); }

    @Provides
    public ShopsPresenter providesShopPresenter() { return new ShopsPresenter(dataManager); }

    @Provides
    public AddProductPresenter providesAddProductPresenter() { return new AddProductPresenter(dataManager); }

    @Provides
    public MainPresenter providesMainPresenter() { return new MainPresenter(dataManager); }

    @Provides
    public ProductsPresenter providesProductPresenter() { return new ProductsPresenter(dataManager); }

    @Provides
    public ViewProductPresenter providesViewProductPresenter() { return new ViewProductPresenter(dataManager); }

    @Provides
    public AddManagerPresenter providesAddManagerPresenter() { return new AddManagerPresenter(dataManager); }

    @Provides ApiServiceManager providesApiServiceManager() { return apiServiceManager; }

    @Provides
    @ActivityContext
    public Context providesContext() { return activity; }
}
