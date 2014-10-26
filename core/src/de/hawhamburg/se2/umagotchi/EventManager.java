package de.hawhamburg.se2.umagotchi;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;

/**
 * @author hal
 *
 */
public
class EventManager {
	private
	Map<Class<? extends IEvent>, List<IEventListener>> listeners;
	
	private
	List<IEvent> events;
	
	public
	EventManager () {
		this.listeners = new HashMap<Class<? extends IEvent>, List<IEventListener>> ();
		this.events = new LinkedList<IEvent> ();
	}
	
	public
	void register (IEventListener listener, Class<? extends IEvent> type) {
		if (! this.listeners.containsKey (type)) {
			this.listeners.put (type, new LinkedList<IEventListener> ());
		}
		
		List<IEventListener> listeners = this.listeners.get (type);
		if (! listeners.contains (listener)) {
			Gdx.app.debug ("EventManager", 
				"Adding Listener " + listener + " for " + type
			);
			listeners.add (listener);			
		}
	}
	
	public
	void raise (IEvent event) {
		// do not add event, if no one cares
		if (! this.listeners.containsKey (event.getClass ())) {
			Gdx.app.debug ("EventManager", 
				"Trying to raise " + event.getClass () + " but no one cares!"
			);
		}
		else {
			this.events.add (event);
		}
	}
	
	public
	void fire (IEvent event) {
		// do not add event, if no one cares
		if (! this.listeners.containsKey (event.getClass ())) {
			Gdx.app.debug ("EventManager", 
				"Trying to fire " + event.getClass () + " but no one cares!"
			);
		}
		else {
			boolean consumed = this.process (event);
			if (! consumed) {
				Gdx.app.debug ("EventManager", 
					"Immediate events cannot be requeued! " + event
				);
			}
		}
	}
	
	/**
	 * Processes next event, if one exists. 
	 */
	public
	void tick () {
		if (! this.events.isEmpty ()) {
			IEvent event = this.events.get (0);
			boolean consumed = this.process (event);
			if (consumed) {
				this.events.remove (0);
			}
		}		
	}
	
	/**
	 * Processes event. 
	 * 
	 * @param event
	 * @return true if event has been consumed and can be removed.
	 */
	private
	boolean process (IEvent event) {
		List<IEventListener> listeners = this.listeners.get (event.getClass ());
		
		boolean consumed = false;
		for (IEventListener l : listeners) {
			consumed = l.handle (event);
			if (consumed) {
				break;
			}
		}
		
		return consumed;
	}
}
