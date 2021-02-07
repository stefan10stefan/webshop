package com.lilly021.quickok.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.lilly021.quickok.R;
import com.lilly021.quickok.domain.model.Cart;
import com.lilly021.quickok.domain.model.Product;
import com.lilly021.quickok.ui.base.BaseActivity;
import com.lilly021.quickok.ui.changePassword.ChangePasswordActivity;
import com.lilly021.quickok.ui.main.MainActivity;
import com.lilly021.quickok.ui.viewProduct.ViewProductActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CartActivity extends BaseActivity {

    private ListView shopListView;

    @Inject
    CartPresenter cartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.cart);
        super.onCreate(savedInstanceState);

        activityComponent.inject(this);
        presenter = cartPresenter;

        cartPresenter.setActivity(this);

        get();

    }

    public void get() {

        cartPresenter.get();

    }

    public void buySuccess() {
        startActivity(new Intent(CartActivity.this, MainActivity.class));
    }

    public void success(Cart cart) {
        CartActivity activity = this;
        shopListView = (ListView) findViewById(R.id.cart_list_products);
        CustomCartAdapter adaper = new CustomCartAdapter(this, cart.getProducts());
        shopListView.setAdapter(adaper);

    }

    public void onClickBuy(View view) {
        cartPresenter.buy();
    }
}
