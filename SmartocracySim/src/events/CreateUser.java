package events;

import core.Control;
import core.Sim;
import scheduling.Event;
import scheduling.Schedule;

public class CreateUser extends Event{
		
	public CreateUser(int time){
		super();
		this.start_time = time;
	}
	
	@Override
	public int run() {
		Control.createNewUser(Control.pickProblem());
		Schedule.add(new CreateUser(start_time + Sim.new_user_interval));
		return this.start_time;
	}
}
