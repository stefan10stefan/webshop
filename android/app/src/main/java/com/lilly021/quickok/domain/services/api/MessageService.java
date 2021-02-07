package com.lilly021.quickok.domain.services.api;

import com.lilly021.quickok.domain.model.Message;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface MessageService {

    @GET("api/message/")
    Observable<List<Message>> getMessages();
}
