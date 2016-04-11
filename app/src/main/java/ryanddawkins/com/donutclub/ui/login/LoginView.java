package ryanddawkins.com.donutclub.ui.login;

/**
 * Created by ryan on 3/13/16.
 */
public interface LoginView {

    void navigateToCurrentEvent(String groupId);

    String getPhoneNumber();

    void showMessage(String message);

    boolean hasSmsPermission();

    void showSmsPermissionDialog();

    void onSetupSms();

}
