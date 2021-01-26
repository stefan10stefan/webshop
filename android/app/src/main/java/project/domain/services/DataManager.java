package project.domain.services;

import project.domain.model.AuthResponse;
import project.domain.model.User;
import project.domain.model.request.RegistrationRequest;
import project.domain.services.api.ApiServiceManager;
import project.domain.services.preferences.PreferenceService;
import project.settings.AppSettings;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Observable;

@Singleton
public class DataManager {

    private final PreferenceService preferenceService;
    private final ApiServiceManager apiServiceManager;

    @Inject
    public DataManager(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
        apiServiceManager = new ApiServiceManager(preferenceService);
    }

    public boolean isUserLogin() {
        return preferenceService.isUserLogin();
    }

    // Login

    public Observable<AuthResponse> login(String username, String password) {

        return apiServiceManager.getUserService().login(
                username, password, AppSettings.CLIENT_ID, AppSettings.CLIENT_SECRET,
                        AppSettings.GRANT_TYPE_PASSWORD
        );
    }

    public Observable<User> getCurrentUser() {
        return apiServiceManager.getUserService().getCurrentUser();
    }

    public void setCurrentUser(User user) {

        preferenceService.setUser(user);
    }

    public void setToken(String token) {

        preferenceService.setToken(token);
        preferenceService.setUserLogin(true);
    }

    // Registration

    public Observable<User> registration(String email, String password, String firstName, String lastName) {

        return apiServiceManager.getUserService().addUser(new RegistrationRequest(email, password,
                firstName, lastName));
    }

    // Logout

    public void logout() {
        preferenceService.setToken("");
        preferenceService.setUserLogin(false);
    }
}
