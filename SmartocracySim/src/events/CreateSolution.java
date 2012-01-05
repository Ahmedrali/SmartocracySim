package events;

import core.Control;
import core.Sim;
import scheduling.Event;
import scheduling.Schedule;

public class CreateSolution extends Event{
		
	public CreateSolution(int time){
		super();
		this.start_time = time;
	}
	
	@Override
	public int run() {
		Control.createNewSolution(Control.pickProblem());
		Schedule.add(new CreateSolution(start_time + Sim.new_user_interval));
		return this.start_time;
	}
}
