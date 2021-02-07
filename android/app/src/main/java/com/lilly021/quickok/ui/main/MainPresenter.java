package com.lilly021.quickok.ui.main;

import com.lilly021.quickok.domain.model.Shop;
import com.lilly021.quickok.domain.model.User;
import com.lilly021.quickok.domain.services.DataManager;
import com.lilly021.quickok.ui.base.BasePresenter;
import com.lilly021.quickok.ui.changePassword.ChangePasswordActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainPresenter extends BasePresenter {

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }
}
