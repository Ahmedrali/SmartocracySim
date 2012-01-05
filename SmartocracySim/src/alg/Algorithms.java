package alg;

import java.util.ArrayList;

import core.Sim;

import entities.Solution;
import entities.User;
import entities.Vote;

public class Algorithms {
	
	public static int[] DirectDemocracy(ArrayList<User> users, ArrayList<Solution> sols){
		int[] votes = new int[sols.size()];
		for (User u : users){
			if(!u.getVotedFor().isEmpty())
				votes = DistriputeVotes(votes, u.getVotedFor(), sols, u, u.getParticles());
		}
		return votes;
	}
	
	public static int[] DynamicallyDistributedDemocracy(ArrayList<User> users, ArrayList<Solution> sols){
		int[] votes = new int[sols.size()];
		for (User u : users){
			votes = checkVoteForUser(u, votes, sols, u.getParticles());
		}
		return votes;
	}
	
	public static int[] ProxyVote(ArrayList<User> users, ArrayList<Solution> sols){
		int[] votes = new int[sols.size()];
		
		int[] initParticles = new int[users.size()];
		for (int i = 0; i < users.size(); i++){
			User u = users.get(i);
			initParticles[u.getId()] = u.getParticles() + (u.getTrustCount() * 100);  
		}
		for (User u : users){
			votes = checkVoteForUser(u, votes, sols, initParticles[u.getId()]);
		}
		return votes;
	}
	
	public static int[] checkVoteForUser(User u, int[] votes, ArrayList<Solution> sols, int particles){
		if(!u.getVotedFor().isEmpty())
			votes = DistriputeVotes(votes, u.getVotedFor(), sols, u, particles);
		else 
			if(u.getTrustIn() != null)
				votes = checkVoteForUser(u.getTrustIn(), votes, sols, particles);
		return votes;
	}
		
	public static int[] DistriputeVotes(int[] votes, ArrayList<Vote> user_votes, ArrayList<Solution> sols, User u, int particles){
		for (Vote v : user_votes){
			Solution s = v.getSol();
			float prob = v.getProbabilty();
			int sol_idx = sols.indexOf((Solution) s);
			int newVote = Math.round( prob * particles );
			votes[sol_idx] += newVote;
		}
		return votes;
	}
	
	public static ArrayList<Float> normalize(ArrayList<Integer> votes){
		int sum = 0;
		ArrayList<Float> normalozedVote = new ArrayList<Float>();
		for (int i : votes)
			sum += i;
		for (int i : votes)
			normalozedVote.add( 1/(float)sum );
		return normalozedVote;
	}
}
