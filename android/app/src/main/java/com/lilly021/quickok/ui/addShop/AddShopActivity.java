package com.lilly021.quickok.ui.addShop;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.lilly021.quickok.R;
import com.lilly021.quickok.ui.base.BaseActivity;
import com.lilly021.quickok.ui.login.LoginActivity;
import com.lilly021.quickok.ui.login.LoginPresenter;
import com.lilly021.quickok.ui.main.MainActivity;
import com.lilly021.quickok.ui.registration.RegistrationActivity;
import com.lilly021.quickok.util.ValidationUtil;

import javax.inject.Inject;

public class AddShopActivity extends BaseActivity implements LocationListener {

    private EditText txtName;
    private EditText txtLat;
    private EditText txtLng;

    private TextView tvNameError;
    private TextView tvLatError;
    private TextView tvLngError;

    protected LocationManager locationManager;
    protected LocationListener locationListener;

    @Inject
    AddShopPresenter addShopPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_shop);

        activityComponent.inject(this);
        presenter = addShopPresenter;

        init();

        txtLat.setText("45.24315642911794");
        txtLng.setText("19.83011844834395");

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

            }
        }
    }

    public void onClickAddShop(View view) {

        clearErrors();

        if(!canAddShop()) {
            return;
        }

        showLoader();

        addShopPresenter.addShop(txtName.getText().toString(), Double.parseDouble(txtLat.getText().toString()),
                Double.parseDouble(txtLng.getText().toString()));

    }

    public void addShopSuccess() {

        hideLoader();

        startActivity(new Intent(AddShopActivity.this, MainActivity.class));

        finish();
    }

    public void addShopUnsuccessful() {

        hideLoader();

        clearErrors();

        tvNameError.setText("Error");
        tvNameError.setVisibility(View.VISIBLE);
    }


    private void init() {

        addShopPresenter.setActivity(this);

        txtName = findViewById(R.id.name_add_shop);
        txtLat = findViewById(R.id.lat_add_shop);
        txtLng = findViewById(R.id.lng_add_shop);

        tvNameError = findViewById(R.id.name_error_add_shop);
        tvLatError = findViewById(R.id.lat_error_add_shop);
        tvLngError = findViewById(R.id.lng_error_add_shop);
    }

    private boolean canAddShop() {

        boolean ok = true;
        tvNameError.setVisibility(View.GONE);
        tvLatError.setVisibility(View.GONE);
        tvLngError.setVisibility(View.GONE);


        if(ValidationUtil.isEmpty(txtName.getText())) {
            ok = false;

            tvNameError.setText("Required");
            tvNameError.setVisibility(View.VISIBLE);
        }

        if(ValidationUtil.isEmpty(txtLat.getText())) {
            ok = false;

            tvLatError.setText("Required");
            tvLatError.setVisibility(View.VISIBLE);
        }

        if(ValidationUtil.isEmpty(txtLng.getText())) {
            ok = false;

            tvLngError.setText("Required");
            tvLngError.setVisibility(View.VISIBLE);
        }

        return ok;
    }

    private void clearErrors() {

        tvNameError.setText("");
        tvNameError.setVisibility(View.GONE);

        tvLatError.setText("");
        tvLatError.setVisibility(View.GONE);

        tvLngError.setText("");
        tvLngError.setVisibility(View.GONE);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        txtLat.setText(Double.toString(location.getLatitude()));
        txtLng.setText(Double.toString(location.getLongitude()));
    }
}