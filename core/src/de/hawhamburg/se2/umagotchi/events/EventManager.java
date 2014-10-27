package de.hawhamburg.se2.umagotchi.events;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

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
	ConcurrentLinkedQueue<IEvent> events;
	
	public
	EventManager () {
		this.listeners = new HashMap<Class<? extends IEvent>, List<IEventListener>> ();
		this.events = new ConcurrentLinkedQueue<IEvent> ();
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
		else {
			Gdx.app.debug ("EventManager", 
				"Trying to readd listener " + listener + " for " + type
			);
		}
	}
	
	public
	void unregister (IEventListener listener) {
		for (Class<? extends IEvent> type : this.listeners.keySet ()) {
			this.unregister (listener, type);		
		}
	}
	
	public
	void unregister (IEventListener listener, Class<? extends IEvent> type) {
		if (! this.listeners.containsKey (type)) {
			return;
		}
		
		List<IEventListener> list = this.listeners.get (type);
		if (list.contains (listener)) {
			list.remove (listener);
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
			boolean consumed = this.handle (event);
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
	void process () {
		List<IEvent> buffer = new LinkedList<IEvent> (this.events);
		this.events.clear ();
		for (IEvent event : buffer) {
			boolean consumed = this.handle (event);
			if (consumed) {
				this.raise (event);
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
	boolean handle (IEvent event) {
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
