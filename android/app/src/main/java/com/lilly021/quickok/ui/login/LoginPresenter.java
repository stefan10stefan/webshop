package com.lilly021.quickok.ui.login;

import com.lilly021.quickok.domain.model.AuthResponse;
import com.lilly021.quickok.domain.model.User;
import com.lilly021.quickok.domain.services.DataManager;
import com.lilly021.quickok.ui.base.BaseActivity;
import com.lilly021.quickok.ui.base.BasePresenter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter {


    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void login(String username, String password) {

        dataManager.login(username, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AuthResponse>() {
                    @Override
                    public void onNext(@NonNull AuthResponse authResponse) {

                        dataManager.setToken(authResponse.access_token);
                        getCurrentUser();
                        ((LoginActivity)activity).loginSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        ((LoginActivity)activity).loginUnsuccessful();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void getCurrentUser() {
        dataManager.getCurrentUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<User>() {
                    @Override
                    public void onNext(@NonNull User user) {

                        dataManager.setCurrentUser(user);
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
