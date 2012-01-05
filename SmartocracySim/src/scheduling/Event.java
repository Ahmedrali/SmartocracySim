package scheduling;

public abstract class Event {
	public int start_time;
	public abstract int run();
	
	public int getStart_time() {
		return start_time;
	}
	public void setStart_time(int startTime) {
		start_time = startTime;
	}
}
