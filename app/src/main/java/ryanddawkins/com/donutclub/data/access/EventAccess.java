package ryanddawkins.com.donutclub.data.access;

/**
 * Created by ryan on 3/10/16.
 */
public interface EventAccess {

    void getCurrentEvent(EventCallback eventCallback, String groupId);

}
