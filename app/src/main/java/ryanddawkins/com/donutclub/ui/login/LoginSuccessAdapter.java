package ryanddawkins.com.donutclub.ui.login;

import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/13/16.
 */
public interface LoginSuccessAdapter {

    String getToken();

    User getUser();

}
