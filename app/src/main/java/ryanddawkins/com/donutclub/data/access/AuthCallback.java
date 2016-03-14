package ryanddawkins.com.donutclub.data.access;

/**
 * Created by ryan on 3/13/16.
 */
public interface AuthCallback {

    void onAuthSuccess(String userId);

    void onAuthError(String message);

}
