package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import util.P;

import entities.*;

public class Control {
	
	static final int PARTICLES_NUM = 100;
	static ArrayList<Problem> problems = new ArrayList<Problem>();
	static HashMap<Problem, ArrayList<Solution>> problems_solutions = new HashMap<Problem, ArrayList<Solution>>();
	static HashMap<Problem, ArrayList<User>> problems_users = new HashMap<Problem, ArrayList<User>>();
	
	public static Problem pickProblem(){
		if (problems.isEmpty())
			return null;
		return problems.get( (new Random()).nextInt(problems.size()) );
	}
	
 	public static void createNewProblem(int participants, int solutions){
 		if (problems.size() == Sim.num_problems)
 			return;
		Problem newProblem = Generator.genProblem(problems.size(), participants, solutions);
		problems.add(newProblem);
		problems_solutions.put(newProblem, new ArrayList<Solution>());
		problems_users.put(newProblem, new ArrayList<User>());
		P.rent("C_P", "PID: " + newProblem.getId() + ", Participants: " + newProblem.getParticipants());
	}
	
	public static boolean createNewUser(Problem problem){
		if (problems.isEmpty())
 			return false;
		
		ArrayList<User> users = problems_users.get((Problem)problem);
		if (users.size() >= problem.getParticipants())
			return false;
		User newUser = Generator.genUser(users.size(), (new Random()).nextFloat(), problem, PARTICLES_NUM);
		users.add(newUser);
		P.rent("C_U", "UID: " + newUser.getId() + ", Rationality: " + newUser.getRationality());
		return true;
	}
	
	public static void createNewSolution(Problem problem){
		if (problems.isEmpty())
 			return;
		ArrayList<User> users = problems_users.get((Problem)problem);
		if (users.isEmpty())
 			return;
		ArrayList<Solution> solutions = problems_solutions.get((Problem)problem);
		
		if(solutions.size() >= problem.getSolutions())
			return;
		
		User sol_owner = users.get((new Random()).nextInt(users.size()));
		Solution solution = Generator.genSolution(solutions.size(), sol_owner, problem);
		solutions.add(solution);
		sol_owner.getSolutions().add(solution);
		P.rent("C_S", "PID: " + solution.getProb().getId() + ", SID: " + solution.getId() + ", UID: " + solution.getUser().getId());
	}
	
	public static void createDecision(){
		Problem p = pickProblem();
		if(p == null)
			return;
		ArrayList<User> users = problems_users.get(p);
		if(users.isEmpty())
			return;
		User user = users.get((new Random()).nextInt(users.size()));
		float rnd = (new Random()).nextFloat();
		if(  rnd > user.getRationality()){
			if (users.size() > 1)
				createTrustRelation(p, user);
		}else
			if (user.getRemainingProb() > 0)
				createVote(p, user);
	}
	
	public static boolean createVote(Problem problem, User user){
//		ArrayList<User> users = problems_users.get((Problem)problem);
//		User user = users.get((new Random()).nextInt(users.size()));
//		if( (new Random()).nextFloat() > user.getRationality() && user.getRemainingProb() > 0)
//			return false;
		ArrayList<Solution> solutions = problems_solutions.get((Problem)problem);
		if(solutions.isEmpty())
			return false;
		Solution solution = solutions.get((new Random()).nextInt(solutions.size()));
		float prob = (new Random()).nextFloat();
		if (prob < user.getRemainingProb()){
			user.setRemainingProb(user.getRemainingProb() - prob);
		}else{
			prob = user.getRemainingProb();
			user.setRemainingProb(0);
		}
		Vote v = Generator.genVote(user, problem, solution, prob);
		user.getVotedFor().add(v);
		solution.incVote();
		P.rent("C_V", "UID: " + user.getId() + ", SID: " + solution.getId() + ", V_Prob: " + prob + ", PID: " + problem.getId() + ", V_count: " + solution.getVoteCount());
		return true;
	}
	
	public static boolean createTrustRelation(Problem problem, User user_a){
		ArrayList<User> users = problems_users.get((Problem)problem);
//		if (users.size() < 2)
//			return false;
//		User user_a = users.get((new Random()).nextInt(users.size()));
		User user_b;
		do{
			user_b = users.get((new Random()).nextInt(users.size()));
		}while(user_a == user_b);
		
		if(user_a.getTrustIn() != null && user_b.getTrustIn() != null)
			return false;
		
		if (user_a.getRationality() < user_b.getRationality() && user_a.getTrustIn() == null && !isContainsCycle(user_a, user_b)){
			user_a.setTrustIn(user_b);
			P.rent("C_T", "UID: " + user_a.getId() + " Trust In UID: " + user_b.getId());
			user_b.incTrustCount();
			return true;
		}else if(user_b.getRationality() < user_a.getRationality() && user_b.getTrustIn() == null && !isContainsCycle(user_b, user_a)){
			user_b.setTrustIn(user_a);
			P.rent("C_T", "UID: " + user_b.getId() + " Trust In UID: " + user_a.getId());
			user_a.incTrustCount();
			return true;
		}
		return false;
	}
	
	public static boolean isContainsCycle(User user_a, User user_b){
		ArrayList<Integer> path = new ArrayList<Integer>();
		if (user_a.getId() == user_b.getId()) 
			return true;
		path.add(user_a.getId());
		User next_user = user_b;
		do{
			int id = next_user.getId();
			if (path.contains(id))
				return true; // Contains Cycle
			path.add(id);
			next_user = next_user.getTrustIn();
		}while(next_user != null);
		return false; // No Cycle
	}
	
}
