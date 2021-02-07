package com.lilly021.quickok.ui.changePassword;

import com.lilly021.quickok.domain.model.User;
import com.lilly021.quickok.domain.services.DataManager;
import com.lilly021.quickok.ui.base.BasePresenter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ChangePasswordPresenter extends BasePresenter {


    public ChangePasswordPresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void changePassword(String password) {

        dataManager.changePassword(password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<User>() {
                    @Override
                    public void onNext(@NonNull User user) {

                        ((ChangePasswordActivity)activity).changePasswordSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        ((ChangePasswordActivity)activity).changePasswordUnsuccessful();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
