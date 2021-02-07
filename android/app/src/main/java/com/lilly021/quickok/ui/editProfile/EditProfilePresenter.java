package com.lilly021.quickok.ui.editProfile;

import com.lilly021.quickok.R;
import com.lilly021.quickok.domain.model.User;
import com.lilly021.quickok.domain.services.DataManager;
import com.lilly021.quickok.ui.base.BasePresenter;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class EditProfilePresenter extends BasePresenter {

    public EditProfilePresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void register(String firstName, String lastName) {

        dataManager.editProfile(dataManager.getUser().getId(), firstName, lastName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<User>() {
                    @Override
                    public void onNext(@NonNull User user) {
                        ((EditProfileActivity)activity).editProfileSuccess();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        ((EditProfileActivity)activity).editProfileError(R.string.user_exists);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
