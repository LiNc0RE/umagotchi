package de.hawhamburg.se2.umagotchi.events;

public
class KeyDownEvent
implements IEvent {

	public final
	int keycode;
	
	public
	KeyDownEvent (int keycode) {
		this.keycode = keycode;
	}
	
}
