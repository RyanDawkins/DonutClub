package ryanddawkins.com.donutclub.data.formatters;

import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/12/16.
 */
public class NameFormatter {

    public static String formatName(User user) {
        return user.getFirstName() + " " + user.getLastName();
    }

}
