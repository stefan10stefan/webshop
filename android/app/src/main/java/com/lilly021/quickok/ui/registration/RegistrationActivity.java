package com.lilly021.quickok.ui.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lilly021.quickok.R;
import com.lilly021.quickok.ui.base.BaseActivity;
import com.lilly021.quickok.ui.login.LoginActivity;
import com.lilly021.quickok.util.ValidationUtil;

import javax.inject.Inject;

public class RegistrationActivity extends BaseActivity {

    private EditText txtEmail;
    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtPassword;

    private TextView tvEmailError;
    private TextView tvFirstNameError;
    private TextView tvLastNameError;
    private TextView tvPasswordError;

    @Inject
    RegistrationPresenter registrationPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        activityComponent.inject(this);
        presenter = registrationPresenter;

        init();
    }

    public void onClickRegistration(View view) {

        clearErrors();

        if(!canRegister()) {
            return;
        }

        showLoader();

        registrationPresenter.register(txtEmail.getText().toString(), txtPassword.getText().toString(),
                txtFirstName.getText().toString(), txtLastName.getText().toString());

    }

    public void registrationSuccess() {

        hideLoader();

        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

        finish();
    }

    public void registrationError(int error) {

        hideLoader();

        clearErrors();

        tvEmailError.setText(getResources().getString(error));
        tvEmailError.setVisibility(View.VISIBLE);
    }

    public void onClickLogin(View view) {

        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));

        finish();
    }

    private void init() {

        registrationPresenter.setActivity(this);

        txtEmail = findViewById(R.id.email_registration);
        txtFirstName = findViewById(R.id.first_name_registration);
        txtLastName = findViewById(R.id.last_name_registration);
        txtPassword = findViewById(R.id.password_registration);

        tvEmailError = findViewById(R.id.email_error_registration);
        tvFirstNameError = findViewById(R.id.first_name_error_registration);
        tvLastNameError = findViewById(R.id.last_name_error_registration);
        tvPasswordError = findViewById(R.id.password_error_registration);
    }

    private void clearErrors() {

        tvEmailError.setText("");
        tvEmailError.setVisibility(View.GONE);

        tvPasswordError.setText("");
        tvPasswordError.setVisibility(View.GONE);

        tvFirstNameError.setText("");
        tvFirstNameError.setVisibility(View.GONE);

        tvLastNameError.setText("");
        tvLastNameError.setVisibility(View.GONE);
    }

    private boolean canRegister() {

        boolean ok = true;
        tvEmailError.setVisibility(View.GONE);
        tvFirstNameError.setVisibility(View.GONE);
        tvLastNameError.setVisibility(View.GONE);
        tvPasswordError.setVisibility(View.GONE);

        if(!ValidationUtil.isValidEmail(txtEmail.getText())) {
            ok = false;

            tvEmailError.setText(R.string.please_enter_valid_email);
            tvEmailError.setVisibility(View.VISIBLE);
        }

        if(ValidationUtil.isEmpty(txtPassword.getText())) {
            ok = false;

            tvPasswordError.setText(R.string.please_enter_valid_password);
            tvPasswordError.setVisibility(View.VISIBLE);
        }

        if(ValidationUtil.isEmpty(txtFirstName.getText())) {
            ok = false;

            tvFirstNameError.setText(R.string.required);
            tvFirstNameError.setVisibility(View.VISIBLE);
        }

        if(ValidationUtil.isEmpty(txtLastName.getText())) {
            ok = false;

            tvLastNameError.setText(R.string.required);
            tvLastNameError.setVisibility(View.VISIBLE);
        }

        return ok;
    }
}
