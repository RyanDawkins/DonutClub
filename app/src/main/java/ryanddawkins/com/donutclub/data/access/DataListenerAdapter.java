package ryanddawkins.com.donutclub.data.access;

/**
 * Created by ryan on 3/5/16.
 */
public interface DataListenerAdapter {

    /**
     * This method is to detatch the listener to prevent memory leaks.
     */
    void stopListening();

}
