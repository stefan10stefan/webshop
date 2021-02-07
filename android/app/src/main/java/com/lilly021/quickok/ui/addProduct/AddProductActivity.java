package com.lilly021.quickok.ui.addProduct;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lilly021.quickok.R;
import com.lilly021.quickok.ui.base.BaseActivity;
import com.lilly021.quickok.ui.main.MainActivity;
import com.lilly021.quickok.util.ValidationUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

import javax.inject.Inject;

public class AddProductActivity extends BaseActivity {

    private EditText txtName;
    private EditText txtDescription;
    private EditText txtPrice;

    private TextView tvNameError;
    private TextView tvDescriptionError;
    private TextView tvPriceError;
    private Long shopId;
    private String image;

    @Inject
    AddProductPresenter addProductPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        activityComponent.inject(this);
        presenter = addProductPresenter;

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            shopId =(Long) b.get("shopId");
        }

        init();
    }

    public void onClickAddProduct(View view) {

        clearErrors();

        if(!canAddProduct()) {
            return;
        }

        showLoader();

        addProductPresenter.addProduct(txtName.getText().toString(), txtDescription.getText().toString(),
                Double.parseDouble(txtPrice.getText().toString()), image, shopId);

    }

    public void addProductSuccess() {

        hideLoader();

        startActivity(new Intent(AddProductActivity.this, MainActivity.class));

        finish();
    }

    public void addProductUnsuccessful() {

        hideLoader();

        clearErrors();

        tvNameError.setText("Error");
        tvNameError.setVisibility(View.VISIBLE);
    }


    private void init() {

        addProductPresenter.setActivity(this);

        txtName = findViewById(R.id.name_add_product);
        txtDescription = findViewById(R.id.description_add_product);
        txtPrice = findViewById(R.id.price_add_product);

        tvNameError = findViewById(R.id.name_error_add_product);
        tvDescriptionError = findViewById(R.id.description_error_add_product);
        tvPriceError = findViewById(R.id.price_error_add_product);
    }

    private boolean canAddProduct() {

        boolean ok = true;
        tvNameError.setVisibility(View.GONE);
        tvDescriptionError.setVisibility(View.GONE);
        tvPriceError.setVisibility(View.GONE);


        if(ValidationUtil.isEmpty(txtName.getText())) {
            ok = false;

            tvNameError.setText("Required");
            tvNameError.setVisibility(View.VISIBLE);
        }

        if(ValidationUtil.isEmpty(txtDescription.getText())) {
            ok = false;

            tvDescriptionError.setText("Required");
            tvDescriptionError.setVisibility(View.VISIBLE);
        }

        if(ValidationUtil.isEmpty(txtPrice.getText())) {
            ok = false;

            tvPriceError.setText("Required");
            tvPriceError.setVisibility(View.VISIBLE);
        }

        return ok;
    }

    private void clearErrors() {

        tvNameError.setText("");
        tvNameError.setVisibility(View.GONE);

        tvDescriptionError.setText("");
        tvDescriptionError.setVisibility(View.GONE);

        tvPriceError.setText("");
        tvPriceError.setVisibility(View.GONE);
    }

    public void onClickAddImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), 0);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 0) {
                Uri imageUri = Objects.requireNonNull(data).getData();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (bitmap != null) {

                    byte[] imageBytes = imageToByteArray(bitmap);
                    String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT); // actual conversion to Base64 String Image
                    image = encodedImage;
                }

            }
        }
    }

    private byte[] imageToByteArray(Bitmap bitmapImage) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        return baos.toByteArray();
    }
}