package project.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import project.ui.base.BaseActivity;
import project.ui.login.LoginActivity;
import project.ui.main.MainActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

    @Inject SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent.inject(this);

        if(splashPresenter.isUserLogin())
        {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }
        else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }

        finish();
    }
}
