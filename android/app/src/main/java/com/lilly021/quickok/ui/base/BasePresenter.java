package com.lilly021.quickok.ui.base;

import com.lilly021.quickok.domain.services.DataManager;
import com.lilly021.quickok.events.BaseEvent;
import com.lilly021.quickok.events.EventType;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class BasePresenter {

    protected final DataManager dataManager;
    protected BaseActivity activity;

    @Inject
    public BasePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public void setActivity(BaseActivity activity) {
        this.activity = activity;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBaseEvent(BaseEvent event)
    {
        if(event.getType() == EventType.Logout) {
            dataManager.logout();
            activity.goToLogin();
        }
    }
}
