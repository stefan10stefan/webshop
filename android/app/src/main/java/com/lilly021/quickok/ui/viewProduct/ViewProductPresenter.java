package com.lilly021.quickok.ui.viewProduct;

import com.lilly021.quickok.domain.model.Cart;
import com.lilly021.quickok.domain.model.Product;
import com.lilly021.quickok.domain.services.DataManager;
import com.lilly021.quickok.ui.base.BasePresenter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ViewProductPresenter extends BasePresenter {


    public ViewProductPresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void getProduct(Long productId) {

        dataManager.getProduct(productId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Product>() {
                    @Override
                    public void onNext(@NonNull Product product) {

                        ((ViewProductActivity)activity).viewProductSuccess(product);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void addProductCart(Long productId) {

        dataManager.addProductCart(productId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Cart>() {
                    @Override
                    public void onNext(@NonNull Cart cart) {

                        ((ViewProductActivity)activity).viewProductCartSuccess(cart);
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
