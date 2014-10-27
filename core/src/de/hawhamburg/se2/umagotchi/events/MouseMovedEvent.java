package de.hawhamburg.se2.umagotchi.events;

public
class MouseMovedEvent
implements IEvent {

	public final
	int screenX;
	
	public final
	int screenY;
	
	public
	MouseMovedEvent (int screenX, int screenY) {
		this.screenX = screenX;
		this.screenY = screenY;
	}
	
}
