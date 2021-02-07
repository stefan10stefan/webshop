package com.lilly021.quickok.ui.products;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.lilly021.quickok.R;
import com.lilly021.quickok.domain.model.Product;
import com.lilly021.quickok.domain.model.Shop;
import com.lilly021.quickok.ui.addProduct.AddProductActivity;
import com.lilly021.quickok.ui.base.BaseActivity;
import com.lilly021.quickok.ui.viewProduct.ViewProductActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ProductsActivity extends BaseActivity {

    private EditText txtSearch;
    private List<Product> products = new ArrayList<>();
    private ListView shopListView;

    @Inject
    ProductsPresenter productsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.products);
        super.onCreate(savedInstanceState);

        activityComponent.inject(this);
        presenter = productsPresenter;

        txtSearch = findViewById(R.id.term_search_product);

        productsPresenter.setActivity(this);

        searchProductClick();

    }

    public void searchProductClick() {

        productsPresenter.search(txtSearch.getText().toString());

    }

    public void success(List<Product> products) {
        ProductsActivity activity = this;
        this.products = products;
        shopListView = (ListView) findViewById(R.id.list_products);
        CustomProductAdapter adaper = new CustomProductAdapter(this, products);
        shopListView.setAdapter(adaper);

        shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product o = (Product) shopListView.getItemAtPosition(position);
                Intent in = new Intent(activity, ViewProductActivity.class);
                in.putExtra("productId", o.getId());
                startActivity(in);
            }
        });
    }

    public void searchProductClick(View view) {
        productsPresenter.search(txtSearch.getText().toString());

    }
}
