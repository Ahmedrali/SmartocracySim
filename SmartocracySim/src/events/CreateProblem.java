package events;

import core.Control;
import core.Sim;
import scheduling.Event;
import scheduling.Schedule;

public class CreateProblem extends Event{
	
	public CreateProblem(int time){
		super();
		this.start_time = time;
	}
	
	@Override
	public int run() {
		Control.createNewProblem(Sim.participants, Sim.num_solutions);
		Schedule.add(new CreateProblem(start_time + Sim.new_problem_interval));
		return this.start_time;
	}
}
