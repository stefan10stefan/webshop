package project.domain.services.api;

import project.domain.model.AuthResponse;
import project.domain.model.User;
import project.domain.model.request.RegistrationRequest;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @GET("user/current")
    Observable<User> getCurrentUser();

    @POST("user/")
    Observable<User> addUser(@Body RegistrationRequest reqeust);

    @FormUrlEncoded
    @POST("oauth/token")
    Observable<AuthResponse> login(@Field("username") String username, @Field("password") String password,
                                   @Field("client_id") String clientId, @Field("client_secret") String clientSecret,
                                   @Field("grant_type") String grantType);
}
