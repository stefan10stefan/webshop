package com.lilly021.quickok.ui.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.lilly021.quickok.MapsActivity;
import com.lilly021.quickok.R;
import com.lilly021.quickok.events.BaseEvent;
import com.lilly021.quickok.events.EventType;
import com.lilly021.quickok.injection.component.ActivityComponent;
import com.lilly021.quickok.injection.component.DaggerActivityComponent;
import com.lilly021.quickok.injection.module.ActivityModule;
import com.lilly021.quickok.ui.addManager.AddManagerActivity;
import com.lilly021.quickok.ui.addShop.AddShopActivity;
import com.lilly021.quickok.ui.cart.CartActivity;
import com.lilly021.quickok.ui.changePassword.ChangePasswordActivity;
import com.lilly021.quickok.ui.editProfile.EditProfileActivity;
import com.lilly021.quickok.ui.login.LoginActivity;
import com.lilly021.quickok.ui.messages.MessagesActivity;
import com.lilly021.quickok.ui.products.ProductsActivity;
import com.lilly021.quickok.ui.shops.ShopsActivity;
import com.lilly021.quickok.ui.splash.SplashActivity;

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
            case R.id.nav_item_change_password:
                changePassword();
                return true;
            case R.id.nav_item_add_shop:
                addShop();
                return true;
            case R.id.nav_item_shops:
                shops();
                return true;
            case R.id.nav_item_products:
                products();
                return true;
            case R.id.nav_item_add_manager:
                addManager();
                return true;
            case R.id.nav_item_edit_profile:
                editProfile();
                return true;
            case R.id.nav_item_messages:
                showMessages();
                return true;
            case R.id.nav_item_cart:
                showCart();
                return true;
            case R.id.nav_item_map:
                showMap();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showMap() {
        startActivity(new Intent(BaseActivity.this, MapsActivity.class));
    }

    public void showCart() {
        startActivity(new Intent(BaseActivity.this, CartActivity.class));
    }

    public void showMessages() {
        startActivity(new Intent(BaseActivity.this, MessagesActivity.class));
    }

    public void editProfile() {
        startActivity(new Intent(BaseActivity.this, EditProfileActivity.class));
    }

    public void changePassword() {
        startActivity(new Intent(BaseActivity.this, ChangePasswordActivity.class));

    }

    public void shops() {
        startActivity(new Intent(BaseActivity.this, ShopsActivity.class));

    }

    public void addManager() {
        startActivity(new Intent(BaseActivity.this, AddManagerActivity.class));

    }

    public void products() {
        startActivity(new Intent(BaseActivity.this, ProductsActivity.class));

    }

    public void addShop() {
        startActivity(new Intent(BaseActivity.this, AddShopActivity.class));

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
