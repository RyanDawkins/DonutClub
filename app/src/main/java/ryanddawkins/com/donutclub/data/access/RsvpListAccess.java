package ryanddawkins.com.donutclub.data.access;

import java.util.Date;

/**
 * Created by ryan on 3/4/16.
 */
public interface RsvpListAccess {

    DataListenerAdapter getRsvpList(GetUserCallback getUserCallback, Date eventDate);

}
