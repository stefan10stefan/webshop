package project.ui.main;

import android.os.Bundle;

import com.lilly021.webshop.R;
import project.ui.base.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        activityComponent.inject(this);
        presenter = mainPresenter;

    }

}