package de.hawhamburg.se2.umagotchi.events;

public
class KeyUpEvent
implements IEvent {

	public final
	int keycode;
	
	public
	KeyUpEvent (int keycode) {
		this.keycode = keycode;
	}
	
}
