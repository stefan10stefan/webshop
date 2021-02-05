package com.lilly021.quickok.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.lilly021.quickok.R;
import com.lilly021.quickok.domain.model.Shop;
import com.lilly021.quickok.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

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
        mainPresenter.setActivity(this);

    }

}