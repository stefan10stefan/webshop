package com.lilly021.quickok.ui.messages;

import com.lilly021.quickok.domain.model.Message;
import com.lilly021.quickok.domain.model.Shop;
import com.lilly021.quickok.domain.services.DataManager;
import com.lilly021.quickok.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MessagesPresenter extends BasePresenter {

    public MessagesPresenter(DataManager dataManager) {
        super(dataManager);
    }

    public void getMessages() {

        dataManager.getMessages()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Message>>() {
                    @Override
                    public void onNext(@NonNull List<Message> messages) {

                        ((MessagesActivity)activity).success(messages);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        ((MessagesActivity)activity).success(new ArrayList<>());
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
