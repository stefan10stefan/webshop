package com.lilly021.quickok.ui.products;

import com.lilly021.quickok.domain.model.Product;
import com.lilly021.quickok.domain.model.Shop;
import com.lilly021.quickok.domain.services.DataManager;
import com.lilly021.quickok.ui.base.BasePresenter;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProductsPresenter extends BasePresenter {

    public ProductsPresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void search(String term) {

        dataManager.searchProduct(term)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Product>>() {
                    @Override
                    public void onNext(@NonNull List<Product> products) {

                        ((ProductsActivity)activity).success(products);
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
