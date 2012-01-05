package util;

import core.Sim;

public class P {

	public static void rent(String fnc, String str){
		System.out.println(Sim.current_sim_time + " - Fnc: " + fnc + " : " + str);
	}
}
