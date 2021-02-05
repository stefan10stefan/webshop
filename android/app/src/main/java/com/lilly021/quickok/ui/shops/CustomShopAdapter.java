package com.lilly021.quickok.ui.shops;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lilly021.quickok.R;
import com.lilly021.quickok.domain.model.Shop;

import java.util.List;

public class CustomShopAdapter extends ArrayAdapter<Shop> {

    private List<Shop> shops;

    public CustomShopAdapter(Context context, List<Shop> shops) {
        super(context, R.layout.shop_list_item,shops);

        this.shops = shops;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Shop shop = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shop_list_item, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.shop_name);

        tvName.setText(shop.getName());

        return convertView;

    }


}
