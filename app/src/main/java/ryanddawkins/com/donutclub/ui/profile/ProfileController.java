package ryanddawkins.com.donutclub.ui.profile;

import ryanddawkins.com.donutclub.data.access.GetUserCallback;
import ryanddawkins.com.donutclub.data.pojo.User;
import ryanddawkins.com.donutclub.data.services.UserService;

/**
 * Created by ryan on 3/6/16.
 */
public class ProfileController implements GetUserCallback {

    private UserService userService;
    private ProfileView profileView;
    private User user;

    public ProfileController(ProfileView profileView, UserService userService) {
        this.userService = userService;
        this.profileView = profileView;
    }

    /**
     * Gets the user from the service and passes along the callback
     * @param username
     */
    public void getUser(String username) {
        this.userService.getUser(this, username);
    }

    /**
     * Loads the data back into the view
     * @param user
     */
    @Override
    public void onUserRetrieved(User user) {
        this.user = user;
        this.profileView.setName(user.getFirstName() + user.getLastName());
        this.profileView.setEmail(user.getEmail());
        this.profileView.setPhone(user.getPhoneNumber());
    }
}
