package scheduling;

import java.util.ArrayList;

import core.Sim;
import events.CreateDecision;
import events.CreateProblem;
import events.CreateSolution;
import events.CreateUser;

public class Schedule {
	static ArrayList<Event> events = new ArrayList<Event>();
	
	public static void init(){
		events.add(new CreateProblem(0));
		events.add(new CreateUser(0));
		events.add(new CreateSolution(0));
		events.add(new CreateDecision(0));
	}
	
	public static void add(Event ev){
		for (int i = 0; i < events.size(); i++){
			if(ev.getStart_time() <= events.get(i).getStart_time()){
				events.add(i, ev);
				return;
			}
		}
		events.add(ev);
	}
	
	public static int runNextEvent(){
		Event ev = events.remove(0);
		return ev.run();
	}
}
