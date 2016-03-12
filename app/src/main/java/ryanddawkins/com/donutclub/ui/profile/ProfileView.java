package ryanddawkins.com.donutclub.ui.profile;

/**
 * Created by ryan on 3/6/16.
 */
public interface ProfileView {

    /**
     * Method to put the name into the view.
     * @param name
     */
    void setName(String name);

    /**
     * Method to put the phone into the view
     * @param phone
     */
    void setPhone(String phone);

    /**
     * Method to put the email into the view.
     * @param email
     */
    void setEmail(String email);

    /**
     * Calls the number from a user.
     * @param number
     */
    void callNumber(String number);

}
