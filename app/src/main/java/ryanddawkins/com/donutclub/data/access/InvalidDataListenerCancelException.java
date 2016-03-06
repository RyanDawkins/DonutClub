package ryanddawkins.com.donutclub.data.access;

/**
 * Created by ryan on 3/5/16.
 */
public class InvalidDataListenerCancelException extends RuntimeException {
    
    public InvalidDataListenerCancelException(String detailMessage) {
        super(detailMessage);
    }
}
