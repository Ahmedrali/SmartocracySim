package entities;

public class Solution {
	private User user;
	private int id;
	private float norm;
	private int particles;
	private Problem problem;
	private int voteCount;

	public Solution(int id, User user, Problem problem) {
		super();
		this.id = id;
		this.problem = problem;
		this.user = user;
	}

	public void incVote(){
		voteCount++;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getNorm() {
		return norm;
	}

	public void setNorm(float norm) {
		this.norm = norm;
	}

	public int getParticals() {
		return particles;
	}

	public void setParticals(int particles) {
		this.particles = particles;
	}

	public Problem getProb() {
		return problem;
	}

	public void setProb(Problem prob) {
		this.problem = prob;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
