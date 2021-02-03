package project.ui.base;

import project.domain.services.DataManager;
import project.events.BaseEvent;
import project.events.EventType;

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
