package com.lilly021.quickok.ui.messages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.lilly021.quickok.R;
import com.lilly021.quickok.domain.model.Message;
import com.lilly021.quickok.domain.model.Shop;
import com.lilly021.quickok.ui.addProduct.AddProductActivity;
import com.lilly021.quickok.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MessagesActivity extends BaseActivity {

    private List<Message> messages = new ArrayList<>();
    private ListView messageListView;

    @Inject
    MessagesPresenter messagesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.messages);
        super.onCreate(savedInstanceState);

        activityComponent.inject(this);
        presenter = messagesPresenter;

        messagesPresenter.setActivity(this);

        get();

    }

    public void get() {
        messagesPresenter.getMessages();
    }

    public void success(List<Message> messages) {
        MessagesActivity activity = this;
        this.messages = messages;
        messageListView = (ListView) findViewById(R.id.list_messages);
        CustomMessageAdapter adaper = new CustomMessageAdapter(this, messages);
        messageListView.setAdapter(adaper);
    }

}
