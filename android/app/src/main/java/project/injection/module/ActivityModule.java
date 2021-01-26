package project.injection.module;

import android.content.Context;
import android.app.Activity;

import project.domain.services.DataManager;
import project.domain.services.api.ApiServiceManager;
import project.domain.services.preferences.PreferenceService;
import project.injection.ActivityContext;
import project.ui.login.LoginPresenter;
import project.ui.main.MainPresenter;
import project.ui.registration.RegistrationPresenter;
import project.ui.splash.SplashPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    protected final Activity activity;
    protected final DataManager dataManager;
    protected final PreferenceService preferenceService;
    protected final ApiServiceManager apiServiceManager;

    public ActivityModule(Activity activity) {
        this.activity = activity;
        preferenceService = new PreferenceService(activity);
        dataManager = new DataManager(preferenceService);
        apiServiceManager = new ApiServiceManager(preferenceService);
    }

    @Provides
    public Activity providesActivity() { return activity; }

    @Provides
    public DataManager providesDataManager() { return dataManager; }

    @Provides
    public PreferenceService providesPreferenceService() { return preferenceService; }

    @Provides
    public SplashPresenter providesSplashPresenter() { return new SplashPresenter(dataManager); }

    @Provides
    public LoginPresenter providesLoginPresenter() { return new LoginPresenter(dataManager); }

    @Provides
    public RegistrationPresenter providesRegistrationPresenter() { return new RegistrationPresenter(dataManager); }

    @Provides
    public MainPresenter providesMainPresenter() { return new MainPresenter(dataManager); }

    @Provides ApiServiceManager providesApiServiceManager() { return apiServiceManager; }

    @Provides
    @ActivityContext
    public Context providesContext() { return activity; }
}
