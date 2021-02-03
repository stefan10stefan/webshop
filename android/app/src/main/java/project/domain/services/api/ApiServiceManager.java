package project.domain.services.api;

import project.domain.services.preferences.PreferenceService;
import project.settings.AppSettings;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import hu.akarnokd.rxjava3.retrofit.*;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceManager {

    private final Retrofit retrofit;
    private UserService userService;
    private PreferenceService preferenceService;

    @Inject
    public ApiServiceManager(PreferenceService preferenceService)
    {
        this.preferenceService = preferenceService;
        retrofit = createRetrofit();
    }

    public UserService getUserService() {

        if(userService == null) {
            userService = retrofit.create(UserService.class);
        }

        return userService;
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(AppSettings.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(createOkHttpClient())
                .build();
    }

    private OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new RequestInterceptor(preferenceService));
        return httpClient.build();
    }
}
