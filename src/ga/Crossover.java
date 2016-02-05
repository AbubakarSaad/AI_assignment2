package ga;

import java.util.*;

/**
 * 
 * @author abubakarsaad
 *
 */
public class Crossover {

	// Two type of crossovers

	//1. Uniform order crossover(UOX, with bit mask)
	//	step1. generate a random bit mask which has same length as parent1 and parent2
	//  step2. use the bit mask to do crossover and generate 2 children
	
	//2. Partially mapped crossover(PMX)
	
	// get the fittest individual and crossover them
	// first its uniform order crossover
	// 2nd its partially mapped crossover(PMX)
	
	//fittest individuals
	String[] parents_crossover;
	String[] child;
	String[][] children;
	int[] bit_mask;
	
	
	Random rand = new Random();
	int index;
	
	
	public Crossover(){
		
		bit_mask();
	}
	
	
	
	public void print(){
//		for(int i=0; i<parents.length; i++){
//			System.out.println(parents[i]);
//		
//		}
		
		for(int i=0; i<children.length; i++){
			for(int j=0; j<children[i].length; j++){
				System.out.print(children[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	public void Initailize_crossover(String[] parents, String[][] routes){
      //repro.Initailize_arrays(coord, routes);
		children = new String[2][routes[0].length];
		parents_crossover = parents;
		for(int i=0; i<2; i++){
			if(i == 0){
				Uniform_order_crossover(routes, i, i+1, i);
			}else if(i == 1){
				Uniform_order_crossover(routes, i, i-1, i);
			}
		}
		// uncommet this to run partially and comment the for loop above
		//Partially_mapped_crossover(routes);
		
	}
	// generte a random mass and whereever its 1 store the p1 value in the child array at the same index else dont store anything
	// once its filled go to p2 and // refered to the book
	
	public void Uniform_order_crossover(String[][] routes, int p1, int p2, int count){
		int parent_1, parent_2;
		//parents = repro.get_fittest();
		child = new String[routes[0].length];
		
		parent_1 = Integer.parseInt(parents_crossover[p1]);
		parent_2 = Integer.parseInt(parents_crossover[p2]);
		
		
		for(int j=0; j<routes[parent_1].length; j++){
			if(bit_mask[j] == 1){
				child[j] = routes[parent_1][j];
			}
			//System.out.print(bit_mask[j] + " ");
		}
		//System.out.println();	
	
		for(int j=0; j<routes[parent_2].length; j++){
			//System.out.print(check(child,routes[parent_2][j]) + " ");
			if(check(child, routes[parent_2][j]) == false){
				// add to child array only where child array is null
				for(int k=0; k<child.length;k++){
					if(child[k] == null){
						child[k] = routes[parent_2][j];
						break;
					}
					
				}
			}
		}
		//System.out.println();
		
		copy(child, count);
	}
	
	
	public void bit_mask(){
		bit_mask = new int[52];
		for(int i=0; i<bit_mask.length; i++){
			index = rand.nextInt(2);
			bit_mask[i] = index;
		}
	}
	
	public void copy(String[] child, int k){
		for(int i=k; i<children.length; i++){
			children[i] = child;
		}
		
	}
	
	public String[][] get_children(){
		return children;
	}
	
	
	public boolean check(String[] child, String index){
		List<String> list = Arrays.asList(child);
		if(list.contains(index)){
			return true;
		}
		return false;
	}
	

	public void Partially_mapped_crossover(String[][] routes){
		int parent_1, parent_2;
		String[] child_1, child_2;
		
		child_1 = new String[routes[0].length];
		child_2 = new String[routes[0].length];
		parent_1 = Integer.parseInt(parents_crossover[0]);
		parent_2 = Integer.parseInt(parents_crossover[1]);
		
		// pick point in parents, add + 15 to it and swapped all the portion with parent 2 and make everything less null
//		for(int i=0; i<routes[parent_1].length; i++){
//			System.out.print(routes[parent_1][i] + " ");
//		}
//		System.out.println();
//		
//		for(int i=0; i<routes[parent_2].length; i++){
//			System.out.print(routes[parent_2][i] + " ");
//		}
		//System.out.println();
		//System.out.println();
		
		int start_pt = 20;
		int end_pt = 35;
		while(start_pt < end_pt){
			child_1[start_pt] = routes[parent_2][start_pt];
			child_2[start_pt] = routes[parent_1][start_pt];
			start_pt++;
		}
		
		// child_1;
		for(int i=0; i<routes[parent_1].length; i++){
			if((check(child_1, routes[parent_1][i]) == false)){
				if(i < 20){
					child_1[i] = routes[parent_1][i];
				}
				if(i > 34){
					child_1[i] = routes[parent_1][i];	
				}
			}
		}
		
		for(int i=0; i<routes[parent_1].length;i++){
			if(child_1[i] == null){
				for(int j=0; j<child_1.length; j++){
					if(check(child_1, routes[parent_1][j]) == false){
						child_1[i] = routes[parent_1][j];
					}
				}
			}
		}
		
		// child_2
		for(int i=0; i<routes[parent_2].length; i++){
			if(check(child_2, routes[parent_2][i]) == false){
				if(i < 20){
					child_2[i] = routes[parent_2][i];
				}
				if(i > 34){
					child_2[i] = routes[parent_2][i];	
				}
			}
		}
		
		for(int i=0; i<routes[parent_2].length;i++){
			if(child_2[i] == null){
				for(int j=0; j<child_2.length; j++){
					if(check(child_2, routes[parent_2][j]) == false){
						child_2[i] = routes[parent_2][j];
					}
				}
			}
		}
		
		
		children[0] = child_1;
		children[1] = child_2;
		
	}
	
}
