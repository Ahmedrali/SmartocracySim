package entities;

import java.util.ArrayList;

public class User {
	private int id;
	private float rationality;
	private ArrayList<Vote> votedFor;
	private User trustIn;
	private Problem problem;
	private int particles;
	private ArrayList<Solution> solutions;
	private float remainingProb;
	private int trustCount;

	public User(int id, float rationality, Problem problem, int particles) {
		super();
		this.id = id;
		this.rationality = rationality;
		this.problem = problem;
		this.particles = particles;
		this.votedFor = new ArrayList<Vote>();
		this.trustIn = null;
		this.solutions = new ArrayList<Solution>();
		this.remainingProb = 1.0f;
		this.trustCount = 0;
	}

	public float getRemainingProb() {
		return remainingProb;
	}

	public void setRemainingProb(float remainingProb) {
		this.remainingProb = remainingProb;
	}

	public void incTrustCount() {
		trustCount++;
	}

	public int getTrustCount() {
		return trustCount;
	}

	public void setTrustCount(int trustCount) {
		this.trustCount = trustCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getRationality() {
		return rationality;
	}

	public void setRationality(float rationality) {
		this.rationality = rationality;
	}

	public ArrayList<Vote> getVotedFor() {
		return votedFor;
	}

	public void setVotedFor(ArrayList<Vote> votedFor) {
		this.votedFor = votedFor;
	}

	public ArrayList<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(ArrayList<Solution> solutions) {
		this.solutions = solutions;
	}

	public User getTrustIn() {
		return trustIn;
	}

	public void setTrustIn(User trustIn) {
		this.trustIn = trustIn;
	}

	public Problem getProb() {
		return problem;
	}

	public void setProb(Problem prob) {
		this.problem = prob;
	}

	public int getParticles() {
		return particles;
	}

	public void setParticles(int particles) {
		this.particles = particles;
	}

}
