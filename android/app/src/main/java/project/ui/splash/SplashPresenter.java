package project.ui.splash;

import project.domain.services.DataManager;
import project.ui.base.BasePresenter;

public class SplashPresenter extends BasePresenter {

    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }

    public boolean isUserLogin() {
        return dataManager.isUserLogin();
    }
}
