package project.util;

import android.text.Editable;
import android.util.Patterns;

public class ValidationUtil {

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isEmpty(Editable value) {
        return value == null || value.toString().trim().isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return (!isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public static boolean isValidEmail(Editable email) {
        return (!isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches());
    }
}
