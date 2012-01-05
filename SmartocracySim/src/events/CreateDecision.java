
package events;

import core.Control;
import core.Sim;
import scheduling.Event;
import scheduling.Schedule;

public class CreateDecision extends Event{
		
	public CreateDecision(int time){
		super();
		this.start_time = time;
	}
	
	@Override
	public int run() {
		Control.createDecision();
		Schedule.add(new CreateDecision(start_time + Sim.new_Decision_interval));
		return this.start_time;
	}
}
