package com.lilly021.quickok.ui.viewProduct;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.lilly021.quickok.R;
import com.lilly021.quickok.domain.model.Cart;
import com.lilly021.quickok.domain.model.Product;
import com.lilly021.quickok.ui.base.BaseActivity;
import com.lilly021.quickok.ui.main.MainActivity;
import com.lilly021.quickok.util.ValidationUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

import javax.inject.Inject;

public class ViewProductActivity extends BaseActivity {

    private Long productId;

    @Inject
    ViewProductPresenter viewProductPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_product);

        activityComponent.inject(this);
        presenter = viewProductPresenter;

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            productId =(Long) b.get("productId");
        }

        init();

        Spinner spinner = (Spinner) findViewById(R.id.grade_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.grades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void viewProductSuccess(Product product) {

        TextView tvName = findViewById(R.id.product_name);
        TextView tvDescription = findViewById(R.id.product_description);
        TextView tvPrice = findViewById(R.id.product_price);
        ImageView imageView = findViewById(R.id.product_image);

        tvName.setText(product.getName());
        tvDescription.setText(product.getDescription());
        tvPrice.setText(Double.toString(product.getPrice()));

        byte[] decodedString = Base64.decode(product.getImage(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imageView.setImageBitmap(decodedByte);

    }

    public void viewProductCartSuccess(Cart cart) {

    }

    private void init() {

        viewProductPresenter.setActivity(this);

        viewProductPresenter.getProduct(productId);
    }


    public void onClickCart(View view) {
        viewProductPresenter.addProductCart(productId);

    }

    public void onClickGrade(View view) {
    }
}