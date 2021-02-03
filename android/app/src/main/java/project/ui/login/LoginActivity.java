package project.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lilly021.webshop.R;
import project.ui.base.BaseActivity;
import project.ui.main.MainActivity;
import project.ui.registration.RegistrationActivity;
import project.util.ValidationUtil;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity {

    private EditText txtEmail;
    private EditText txtPassword;

    private TextView tvEmailError;
    private TextView tvPasswordError;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        activityComponent.inject(this);
        presenter = loginPresenter;

        init();
    }

    public void onClickLogin(View view) {

        clearErrors();

        if(!canLogin()) {
            return;
        }

        showLoader();

        loginPresenter.login(txtEmail.getText().toString(), txtPassword.getText().toString());

    }

    public void loginSuccess() {

        hideLoader();

        startActivity(new Intent(LoginActivity.this, MainActivity.class));

        finish();
    }

    public void loginUnsuccessful() {

        hideLoader();

        clearErrors();

        tvEmailError.setText(R.string.please_check_your_email_and_password);
        tvEmailError.setVisibility(View.VISIBLE);
    }

    public void onClickRegister(View view) {

        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));

        finish();
    }

    private void init() {

        loginPresenter.setActivity(this);

        txtEmail = findViewById(R.id.email_login);
        txtPassword = findViewById(R.id.password_login);

        tvEmailError = findViewById(R.id.email_error_login);
        tvPasswordError = findViewById(R.id.password_error_login);
    }

    private boolean canLogin() {

        boolean ok = true;
        tvEmailError.setVisibility(View.GONE);
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

        return ok;
    }

    private void clearErrors() {

        tvEmailError.setText("");
        tvEmailError.setVisibility(View.GONE);

        tvPasswordError.setText("");
        tvPasswordError.setVisibility(View.GONE);
    }
}
