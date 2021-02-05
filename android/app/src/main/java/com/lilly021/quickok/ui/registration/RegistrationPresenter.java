package com.lilly021.quickok.ui.registration;

import com.lilly021.quickok.R;
import com.lilly021.quickok.domain.model.User;
import com.lilly021.quickok.domain.services.DataManager;
import com.lilly021.quickok.ui.base.BasePresenter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegistrationPresenter extends BasePresenter {

    public RegistrationPresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void register(String email, String password, String firstName, String lastName) {

        dataManager.registration(email, password, firstName, lastName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<User>() {
                    @Override
                    public void onNext(@NonNull User user) {
                        ((RegistrationActivity)activity).registrationSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        ((RegistrationActivity)activity).registrationError(R.string.user_exists);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
