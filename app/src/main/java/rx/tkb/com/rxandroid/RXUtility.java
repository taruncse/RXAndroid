package rx.tkb.com.rxandroid;

import android.text.TextUtils;
import android.util.Patterns;

public class RXUtility {

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
