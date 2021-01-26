package project.domain.services.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import project.domain.model.User;
import project.injection.ActivityContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferenceService {

    public static final String PREF_FILE_NAME = "lilly_check_pref_file";
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    @Inject
    public PreferenceService(@ActivityContext Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public String getToke() {
        return sharedPreferences.getString("TOKEN", "");
    }

    public void setToken(String token) {
        editor.putString("TOKEN", token);
        editor.apply();

    }

    public boolean isUserLogin() {
        return sharedPreferences.getBoolean("IS_USER_LOGIN", false);
    }

    public void setUserLogin(Boolean isUserLogin) {
        editor.putBoolean("IS_USER_LOGIN", isUserLogin);
        editor.apply();
    }

    public void setUser(User user) {

        Gson gson = new Gson();

        editor.putString("USER", gson.toJson(user));
        editor.apply();
    }

    public User getUser() {

        Gson gson = new Gson();

        return gson.fromJson(sharedPreferences.getString("USER", ""), User.class);
    }


    public void clear() {
        editor.clear().apply();
    }
}
