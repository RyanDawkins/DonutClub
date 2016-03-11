package ryanddawkins.com.donutclub.data.access;

import ryanddawkins.com.donutclub.data.pojo.Event;

/**
 * Created by ryan on 3/10/16.
 */
public interface EventCallback {

    void onRetrievedEvent(Event event);

}
