package core;

import entities.*;

public class Generator {
	
	public static Problem genProblem(int id, int participants, int solutions){
		Problem p = new Problem(id, participants, solutions);
		return p;
	}
	
	public static User genUser(int id, float rationality, Problem problem, int particles){
		User u = new User(id, rationality, problem, particles);
		return u;
	}
	
	public static Solution genSolution(int id, User user, Problem problem){
		Solution s = new Solution(id, user, problem);
		return s;
	}
	
	public static Vote genVote(User user, Problem prob, Solution sol, float probabilty){
		Vote v = new Vote(user, prob, sol, probabilty);
		return v;
	}
}
