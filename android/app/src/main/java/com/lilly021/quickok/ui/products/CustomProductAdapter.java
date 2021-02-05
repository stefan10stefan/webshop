package com.lilly021.quickok.ui.products;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lilly021.quickok.R;
import com.lilly021.quickok.domain.model.Product;
import com.lilly021.quickok.domain.model.Shop;

import java.util.List;

public class CustomProductAdapter extends ArrayAdapter<Product> {

    private List<Product> products;

    public CustomProductAdapter(Context context, List<Product> products) {
        super(context, R.layout.product_list_item,products);

        this.products = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Product product = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shop_list_item, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.shop_name);

        tvName.setText(product.getName());

        return convertView;

    }


}
