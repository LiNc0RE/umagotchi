package de.hawhamburg.se2.umagotchi.events;

public
class TouchDraggedEvent
implements IEvent {

	public final
	int screenX;
	
	public final
	int screenY;
	
	public final
	int pointer;
	
	public
	TouchDraggedEvent (int screenX, int screenY, int pointer) {
		this.screenX = screenX;
		this.screenY = screenY;
		this.pointer = pointer;
	}
	
}
