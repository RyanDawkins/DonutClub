package ryanddawkins.com.donutclub.data.services;

import ryanddawkins.com.donutclub.data.access.EventAccess;
import ryanddawkins.com.donutclub.data.access.EventCallback;

/**
 * Created by ryan on 3/10/16.
 */
public class EventServiceImplementation implements EventService {

    private EventAccess eventAccess;

    public EventServiceImplementation(EventAccess eventAccess) {
        this.eventAccess = eventAccess;
    }

    @Override
    public void getCurrentEvent(EventCallback eventCallback, String groupId) {
        this.eventAccess.getCurrentEvent(eventCallback, groupId);
    }
}
