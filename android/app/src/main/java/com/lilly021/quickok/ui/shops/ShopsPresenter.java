package com.lilly021.quickok.ui.shops;

import com.lilly021.quickok.domain.model.Shop;
import com.lilly021.quickok.domain.services.DataManager;
import com.lilly021.quickok.ui.base.BasePresenter;
import com.lilly021.quickok.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ShopsPresenter extends BasePresenter {

    public ShopsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void search(String term) {

        dataManager.searchShop(term)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Shop>>() {
                    @Override
                    public void onNext(@NonNull List<Shop> shops) {

                        ((ShopsActivity)activity).success(shops);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
