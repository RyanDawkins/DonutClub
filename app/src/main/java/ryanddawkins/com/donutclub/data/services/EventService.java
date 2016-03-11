package ryanddawkins.com.donutclub.data.services;

import ryanddawkins.com.donutclub.data.access.EventCallback;

/**
 * Created by ryan on 3/10/16.
 */
public interface EventService {

    void getCurrentEvent(EventCallback eventCallback, String groupId);

}
