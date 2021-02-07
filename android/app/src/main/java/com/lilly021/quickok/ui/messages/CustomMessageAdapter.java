package com.lilly021.quickok.ui.messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lilly021.quickok.R;
import com.lilly021.quickok.domain.model.Message;
import com.lilly021.quickok.domain.model.Shop;

import java.util.List;

public class CustomMessageAdapter extends ArrayAdapter<Message> {

    private List<Message> messages;

    public CustomMessageAdapter(Context context, List<Message> messages) {
        super(context, R.layout.message_list_item,messages);

        this.messages = messages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Message message = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_list_item, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.message);

        tvName.setText(message.getMessage());

        return convertView;

    }


}
