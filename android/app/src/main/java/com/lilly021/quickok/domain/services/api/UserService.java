package com.lilly021.quickok.domain.services.api;

import com.lilly021.quickok.domain.model.AuthResponse;
import com.lilly021.quickok.domain.model.User;
import com.lilly021.quickok.domain.model.request.ChangePasswordRequest;
import com.lilly021.quickok.domain.model.request.EditProfileRequest;
import com.lilly021.quickok.domain.model.request.LoginRequest;
import com.lilly021.quickok.domain.model.request.RegistrationRequest;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {

    @GET("api/user/current")
    Observable<User> getCurrentUser();

    @POST("api/user/")
    Observable<User> addUser(@Body RegistrationRequest reqeust);

    @POST("api/user/manager")
    Observable<User> addUserManager(@Body RegistrationRequest reqeust);

    @FormUrlEncoded
    @POST("oauth/token")
    Observable<AuthResponse> login(@Field("username") String username, @Field("password") String password,
                                   @Field("client_id") String clientId, @Field("client_secret") String clientSecret,
                                   @Field("grant_type") String grantType);

    @POST("api/user/change-password")
    Observable<User> changePassword(@Body ChangePasswordRequest reqeust);

    @PUT("api/user/")
    Observable<User> editProfile(@Body EditProfileRequest reqeust);
}
