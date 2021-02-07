package com.lilly021.quickok.ui.cart;

import com.lilly021.quickok.domain.model.Cart;
import com.lilly021.quickok.domain.model.Product;
import com.lilly021.quickok.domain.services.DataManager;
import com.lilly021.quickok.ui.base.BasePresenter;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CartPresenter extends BasePresenter {

    public CartPresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void get() {

        dataManager.getCart()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Cart>() {
                    @Override
                    public void onNext(@NonNull Cart cart) {

                        ((CartActivity)activity).success(cart);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void buy() {

        dataManager.buy()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Cart>() {
                    @Override
                    public void onNext(@NonNull Cart cart) {

                        ((CartActivity)activity).buySuccess();
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
