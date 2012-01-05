package entities;

public class Vote {
	private User user;
	private Problem problem;
	private Solution sol;
	private float probabilty;
	
	public Vote(User user, Problem prob, Solution sol, float probabilty) {
		super();
		this.user = user;
		this.problem = prob;
		this.sol = sol;
		this.probabilty = probabilty;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Problem getProb() {
		return problem;
	}
	public void setProb(Problem prob) {
		this.problem = prob;
	}
	public Solution getSol() {
		return sol;
	}
	public void setSol(Solution sol) {
		this.sol = sol;
	}
	public float getProbabilty() {
		return probabilty;
	}
	public void setProbabilty(float probabilty) {
		this.probabilty = probabilty;
	}
	
	
}
