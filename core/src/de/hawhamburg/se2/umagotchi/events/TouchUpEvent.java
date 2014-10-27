package de.hawhamburg.se2.umagotchi.events;

public
class TouchUpEvent
implements IEvent {

	public final
	int screenX;
	
	public final
	int screenY;
	
	public final
	int pointer;
	
	public final
	int button;
	
	public
	TouchUpEvent (int screenX, int screenY, int pointer, int button) {
		this.screenX = screenX;
		this.screenY = screenY;
		this.pointer = pointer;
		this.button = button;
	}
	
}
