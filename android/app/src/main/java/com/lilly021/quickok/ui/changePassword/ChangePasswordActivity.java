package com.lilly021.quickok.ui.changePassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lilly021.quickok.R;
import com.lilly021.quickok.ui.base.BaseActivity;
import com.lilly021.quickok.ui.main.MainActivity;
import com.lilly021.quickok.util.ValidationUtil;

import javax.inject.Inject;

public class ChangePasswordActivity extends BaseActivity {

    private EditText txtPassword;

    private TextView tvPasswordError;

    @Inject
    ChangePasswordPresenter changePasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);

        activityComponent.inject(this);

        init();
    }

    public void onClickChangePassword(View view) {

        clearErrors();

        if(!canLogin()) {
            return;
        }

        showLoader();

        changePasswordPresenter.changePassword( txtPassword.getText().toString());

    }

    public void changePasswordSuccess() {

        hideLoader();

        startActivity(new Intent(ChangePasswordActivity.this, MainActivity.class));

        finish();
    }

    public void changePasswordUnsuccessful() {

        hideLoader();

        clearErrors();
    }

    private void init() {

        changePasswordPresenter.setActivity(this);

        txtPassword = findViewById(R.id.password_login);

        tvPasswordError = findViewById(R.id.password_error_login);
    }

    private boolean canLogin() {

        boolean ok = true;
        tvPasswordError.setVisibility(View.GONE);

        if(ValidationUtil.isEmpty(txtPassword.getText())) {
            ok = false;

            tvPasswordError.setText(R.string.please_enter_valid_password);
            tvPasswordError.setVisibility(View.VISIBLE);
        }

        return ok;
    }

    private void clearErrors() {

        tvPasswordError.setText("");
        tvPasswordError.setVisibility(View.GONE);
    }
}
