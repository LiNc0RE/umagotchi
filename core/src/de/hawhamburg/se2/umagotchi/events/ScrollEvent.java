package de.hawhamburg.se2.umagotchi.events;

public
class ScrollEvent
implements IEvent {
	
	public final
	int amount;
	
	public
	ScrollEvent (int amount) {
		this.amount = amount;
	}
	
}
