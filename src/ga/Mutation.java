package ga;

import java.util.*;

/**
 * 
 * @author abubakarsaad
 *
 */
public class Mutation {

	// get the children
	//  swap random index within an array
	//Crossover cross;
	Random rand = new Random();
	int index_1;
	int index_2;
	String[][] mut_children;
	
	public Mutation(){
		
	}
	
	public void Initialize(String[][] childrens, int k){
		//cross.Initailize_crossover(coord,routes);
		//children = cross.get_children();
		mut_children = childrens;
		mutation(mut_children, k);
		
	}
	
	
	public void mutation(String[][] children, int k){
		
		String tmp;
		for(int i=0; i<children.length; i++){
			// change the rand.nextints to the size of the array.
			index_1 = rand.nextInt(k);
			index_2 = rand.nextInt(k);
			//System.out.println("children length: " + children.length + " index1: "+index_1 + " index2: " + index_2);
			
			if( index_1 != index_2){
				tmp = children[i][index_1];
				children[i][index_1] = children[i][index_2];
				children[i][index_2] = tmp;
			}
		}
	}
	
	
	public String[][] get_mut_children(){
		return mut_children;
	}
	
	
	public void print(){
		for(int i=0; i<mut_children.length; i++){
			for(int j=0; j<mut_children[i].length; j++){
				System.out.print(mut_children[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	
	
	
}
