package impl;

import javafx.event.Event;
import javafx.event.EventType;

public class FXCommEvent extends Event{

	private String key;	    
	public static final EventType<FXCommEvent> COMM = new EventType<>(ANY, "COMM");

	
	public FXCommEvent(String key) {
        super(FXCommEvent.COMM);
        this.key = key;
    }
	
	public String getKey(){
		return key;
	}
}
