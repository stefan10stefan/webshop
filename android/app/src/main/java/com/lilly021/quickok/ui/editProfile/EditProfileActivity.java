package com.lilly021.quickok.ui.editProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lilly021.quickok.R;
import com.lilly021.quickok.domain.model.User;
import com.lilly021.quickok.ui.base.BaseActivity;
import com.lilly021.quickok.ui.login.LoginActivity;
import com.lilly021.quickok.ui.main.MainActivity;
import com.lilly021.quickok.util.ValidationUtil;

import javax.inject.Inject;

public class EditProfileActivity extends BaseActivity {

    private EditText txtEmail;
    private EditText txtFirstName;
    private EditText txtLastName;

    private TextView tvFirstNameError;
    private TextView tvLastNameError;

    @Inject
    EditProfilePresenter editProfilePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);

        activityComponent.inject(this);
        presenter = editProfilePresenter;

        init();
    }

    public void onClickRegistration(View view) {

        clearErrors();

        if(!canRegister()) {
            return;
        }

        showLoader();

        editProfilePresenter.register(txtFirstName.getText().toString(), txtLastName.getText().toString());

    }

    public void editProfileSuccess() {

        hideLoader();

        startActivity(new Intent(EditProfileActivity.this, MainActivity.class));

        finish();
    }

    public void editProfileError(int error) {

        hideLoader();

        clearErrors();

    }

    public void onClickLogin(View view) {

        startActivity(new Intent(EditProfileActivity.this, MainActivity.class));

        finish();
    }

    private void init() {

        editProfilePresenter.setActivity(this);

        txtEmail = findViewById(R.id.email_registration);
        txtFirstName = findViewById(R.id.first_name_edit_profile);
        txtLastName = findViewById(R.id.last_name_edit_profile);

        tvFirstNameError = findViewById(R.id.first_name_error_edit_profile);
        tvLastNameError = findViewById(R.id.last_name_error_edit_profile);

        User user = editProfilePresenter.getDataManager().getUser();

        txtEmail.setText(user.getEmail());
        txtFirstName.setText(user.getFirstName());
        txtLastName.setText(user.getLastName());

    }

    private void clearErrors() {

        tvFirstNameError.setText("");
        tvFirstNameError.setVisibility(View.GONE);

        tvLastNameError.setText("");
        tvLastNameError.setVisibility(View.GONE);
    }

    private boolean canRegister() {

        boolean ok = true;
        tvFirstNameError.setVisibility(View.GONE);
        tvLastNameError.setVisibility(View.GONE);


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
