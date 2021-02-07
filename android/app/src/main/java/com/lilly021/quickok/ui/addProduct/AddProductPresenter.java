package com.lilly021.quickok.ui.addProduct;

import com.lilly021.quickok.domain.model.Product;
import com.lilly021.quickok.domain.model.Shop;
import com.lilly021.quickok.domain.services.DataManager;
import com.lilly021.quickok.ui.base.BasePresenter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AddProductPresenter extends BasePresenter {


    public AddProductPresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void addProduct(String name, String description, double price, String image, Long shopId) {

        dataManager.addProduct(name, description, price, image, shopId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Product>() {
                    @Override
                    public void onNext(@NonNull Product product) {

                        ((AddProductActivity)activity).addProductSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        ((AddProductActivity)activity).addProductUnsuccessful();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
