package ryanddawkins.com.donutclub.data.services;

import ryanddawkins.com.donutclub.data.pojo.User;

/**
 * Created by ryan on 3/6/16.
 */
public class FakeAuthService implements AuthService {

    private User currentUser;

    public FakeAuthService() {

        User user = new User();
        user.setFirstName("Ryan");
        user.setLastName("Dawkins");
        user.setEmail("ryan.d.dawkins@gmail.com");
        user.setPhoneNumber("(405) 824-9223");
        user.setUsername("ryan");

        this.setCurrentUser(user);
    }

    /**
     * Fake method to see if a user is logged in. Always returns true...
     * @return
     */
    @Override
    public boolean isSignedin() {
        return true;
    }

    /**
     * Method to get the current user.
     * @return
     */
    @Override
    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

}