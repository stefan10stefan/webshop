package com.lilly021.quickok.ui.splash;

import com.lilly021.quickok.domain.services.DataManager;
import com.lilly021.quickok.ui.base.BasePresenter;

import javax.inject.Inject;

public class SplashPresenter extends BasePresenter {

    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }

    public boolean isUserLogin() {
        return dataManager.isUserLogin();
    }
}
