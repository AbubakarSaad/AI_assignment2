package ga;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.lang.*;
/**
 * This class uses selection torument
 * @author abubakarsaad
 *
 */
public class Reproduction {

	// use Tourment Selection
	FitnessEvalution pop;
	Random rand = new Random();
	int random;
	int duplicate;
	// pick k individulas
	int k;
	// store all the distance of k individuals in result
	double[] result;
	// gets the distance from fitnessEvalution class
	double distance;
	// gets the index of what chromosome it is
	String[] distance_index;
	String[] fittest;
	String[] elite_fittest;
	Double[] copy_result;
	
	
	public Reproduction(){
		pop = new FitnessEvalution();
		fittest = new String[2];
	}
	/**
	 * Use distance and assign it to some vairable very time i get new distance check if its less then it or not
	 * if it not then keep that if it is then reassign that distance to the newest variable
	 * @param coord
	 * @param routes
	 */
	public void Initailize_arrays(ArrayList<Point2D> coord, String[][] routes, int k_size){
		k = k_size;
		result = new double[k];
		distance_index = new String[k];
		
		//System.out.println(routes.length);
		
		for(int i=0; i<k; i++){
			
			random = rand.nextInt(routes.length);
			
			//System.out.println(random);
			distance = pop.getDistance(coord, routes, random);
			
			//System.out.println(distance + " chromsome: " + random);
			result[i] = distance;
			
			distance_index[i] = Double.toString(distance) + "," + Integer.toString(random);
			
		}
		//min_values();
		//print();
		
		//issuse: if it picks the same individual again my array doesnt hold more then 2 and that causes
		// 		an error of array out of bound index, rerun the program fix it for now but still an issues
	}
	
	public void min_values(int pt){
			
		double min = result[0];
		
		for(int i=1; i<result.length;i++){
			//System.out.println(" result: " + result[i]);
			if(result[i] < min){
				min = result[i];
			}
		}
		
		selection(min, pt);
		
	}
	
	// puts the two of the lowest in the fittest array
	public void selection(double min, int pt){
		//System.out.println("Min: " + min);
		
		for(int i=0; i<distance_index.length; i++){
			String[] tmp = distance_index[i].split(",");
			if(Double.parseDouble(tmp[0]) == min){
				fittest[pt] = tmp[1];
			}
		}
	}
	
	
	
	/**
	 * for elitism
	 */
	public void Initailize_arrays_elitism(ArrayList<Point2D> coord, String[][] routes, int pop_size){
		k = routes.length;
		result = new double[k];
		distance_index = new String[k];
		int elite_percent = (int) Math.round((pop_size * 0.1));
		elite_fittest = new String[elite_percent];
		copy_result = new Double[result.length];
		
		
		
		
		for(int i=0; i<k; i++){
			distance = pop.getDistance(coord, routes, i);
			
			
			//System.out.println(distance + " chromsome: " + chromsome);
			result[i] = distance;
			
			distance_index[i] = Double.toString(distance) + "," + Integer.toString(i);
			
		}
		
		
		for(int i=0; i<copy_result.length; i++){
			copy_result[i] = result[i];
		}
		
		//print();
		
		//issuse: if it picks the same individual again my array doesnt hold more then 2 and that causes
		// 		an error of array out of bound index, rerun the program fix it for now but still an issues
	}
	
	public void min_values_elites(int pt){
		
		double min = copy_result[0];
		
		for(int i=1; i<copy_result.length;i++){
			//System.out.println(" copyreuslt: "+ copy_result[i]);
			if(copy_result[i] < min){
				min = copy_result[i];
			}
		}
		
		
		
		selection_elites(min, pt);
		
		//System.out.println("elite_min: " + min);
		int newcheck = Arrays.asList(copy_result).indexOf(min);
		//System.out.println(newcheck);
		copy_result[newcheck] = 10000000000.0000000000;
		
	}
	

	public void selection_elites(double min, int pt){
		//System.out.println("Min: " + min);
		
		for(int i=0; i<distance_index.length; i++){
			String[] tmp = distance_index[i].split(",");
			if(Double.parseDouble(tmp[0]) == min){
				elite_fittest[pt] = tmp[1];
			}
		}
	}
	
	
	
	
	// return the fittest array
	public String[] get_fittest(){
		return fittest;
	}
	
	public String[] get_fittest_elite(){
		return elite_fittest;
	}
 	
//	public void print(){
//		for(int i=0; i<distance_index.length; i++){
//			System.out.println(distance_index[i]);
//		}
//		
//		for(int i=0; i<fittest.length; i++){
//			System.out.println(fittest[i]);
//		}
//	}
	
	
}
