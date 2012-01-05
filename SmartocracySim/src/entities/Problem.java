package entities;

public class Problem {
	private int id;
	private int participants;
	private int solutions;

	public Problem(int id, int participants, int solutions) {
		super();
		this.id = id;
		this.participants = participants;
		this.solutions = solutions;
	}

	public int getSolutions() {
		return solutions;
	}

	public void setSolutions(int solutions) {
		this.solutions = solutions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}

}
