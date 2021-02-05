package com.lilly021.quickok.ui.shops;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.lilly021.quickok.R;
import com.lilly021.quickok.domain.model.Shop;
import com.lilly021.quickok.ui.addProduct.AddProductActivity;
import com.lilly021.quickok.ui.base.BaseActivity;
import com.lilly021.quickok.ui.changePassword.ChangePasswordActivity;
import com.lilly021.quickok.ui.main.MainPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ShopsActivity extends BaseActivity {

    private EditText txtSearch;
    private List<Shop> shops = new ArrayList<>();
    private ListView shopListView;

    @Inject
    ShopsPresenter shopPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.shops);
        super.onCreate(savedInstanceState);

        activityComponent.inject(this);
        presenter = shopPresenter;

        txtSearch = findViewById(R.id.term_search_shop);

        shopPresenter.setActivity(this);

        searchClick();

    }

    public void searchClick() {

        shopPresenter.search(txtSearch.getText().toString());

    }

    public void success(List<Shop> shops) {
        ShopsActivity activity = this;
        this.shops = shops;
        shopListView = (ListView) findViewById(R.id.list_shops);
        CustomShopAdapter adaper = new CustomShopAdapter(this, shops);
        shopListView.setAdapter(adaper);

        shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Shop o = (Shop) shopListView.getItemAtPosition(position);
                Intent in = new Intent(activity, AddProductActivity.class);
                in.putExtra("shopId", o.getId());
                startActivity(in);
            }
        });
    }

    public void searchClick(View view) {
        shopPresenter.search(txtSearch.getText().toString());

    }
}
