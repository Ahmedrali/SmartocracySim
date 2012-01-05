package core;

import org.jfree.ui.RefineryUtilities;

import charts.ColChart;
import entities.Problem;
import alg.Algorithms;
import scheduling.Schedule;

public class Sim {
	public static int total_sim_time = 7 * 24; // 7 days
	public static int current_sim_time = 0;
	public static int num_problems = 1;
	public static int participants = 60;
	public static int num_solutions = 20;
	
	public static int new_problem_interval = 1 * 24; // l day
	public static int new_user_interval = 2;
	public static int new_solution_interval = 4;
	public static int new_Decision_interval = 3;
	
	
	public static void run(){
		Schedule.init();
		while(current_sim_time < total_sim_time){
			current_sim_time = Schedule.runNextEvent();
		}
		Problem p = Control.pickProblem();
		
		int[] votes_DD = Algorithms.DirectDemocracy(Control.problems_users.get(p), Control.problems_solutions.get(p));
		printArr(votes_DD);
		
		int[] votes_DDD = Algorithms.DynamicallyDistributedDemocracy(Control.problems_users.get(p), Control.problems_solutions.get(p));
		printArr(votes_DDD);
		
		int[] votes_PV = Algorithms.ProxyVote(Control.problems_users.get(p), Control.problems_solutions.get(p));
		printArr(votes_PV);
		
        final ColChart chart = new ColChart("Smartocracy Simultion Result", Control.problems_solutions.get(p).size(), votes_DD, votes_DDD, votes_PV);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
	}
	
	public static void main(String[] args){
		run();
	}
	
	public static void printArr(int[] arr){
		for(int i : arr){
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
}
