package com.lilly021.quickok.ui.addShop;

import com.lilly021.quickok.domain.model.Shop;

import com.lilly021.quickok.domain.services.DataManager;
import com.lilly021.quickok.ui.base.BasePresenter;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AddShopPresenter extends BasePresenter {


    public AddShopPresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void addShop(String name, double lat, double lng) {

        dataManager.addShop(name, lat, lng)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Shop>() {
                    @Override
                    public void onNext(@NonNull Shop shop) {

                        ((AddShopActivity)activity).addShopSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        ((AddShopActivity)activity).addShopUnsuccessful();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
