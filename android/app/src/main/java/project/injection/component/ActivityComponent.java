package project.injection.component;

import android.content.Context;


import project.domain.services.preferences.PreferenceService;
import project.injection.ActivityContext;
import project.injection.module.ActivityModule;
import project.ui.login.LoginActivity;
import project.ui.main.MainActivity;
import project.ui.registration.RegistrationActivity;
import project.ui.splash.SplashActivity;
import project.ui.splash.SplashPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);
    void inject(LoginActivity activity);
    void inject(RegistrationActivity activity);
    void inject(MainActivity activity);
    void inject(SplashPresenter splashPresenter);
    void inject(PreferenceService preferenceService);

    @ActivityContext
    Context context();
}
