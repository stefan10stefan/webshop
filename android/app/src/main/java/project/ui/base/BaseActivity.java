package project.ui.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.lilly021.quickok.R;
import project.events.BaseEvent;
import project.injection.component.ActivityComponent;
import project.injection.component.DaggerActivityComponent;
import project.injection.module.ActivityModule;
import project.ui.login.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BaseActivity extends AppCompatActivity {

    protected ActivityComponent activityComponent;
    protected BasePresenter presenter;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build();

        init();
    }

    public void showLoader() {
        progress.show();
    }

    public void hideLoader() {
        progress.dismiss();
    }

    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.layout.activity_main_drawer, menu);

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onOptionsItemSelected);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_item_logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void logout() {
        presenter.getDataManager().logout();
        goToLogin();
    }

    public void goToLogin() {

        startActivity(new Intent(BaseActivity.this, LoginActivity.class));

        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBaseEvent(BaseEvent event) {
    };

    private void init() {

        progress = new ProgressDialog(this);
        progress.setTitle(getResources().getString(R.string.loading));
        progress.setMessage(getResources().getString(R.string.wait_while_loading));
        progress.setCancelable(false);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
    }
}
