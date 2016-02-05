package ga;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * 
 * @author abubakarsaad
 *
 */
public class Mainclass {
	// point2d library
	FitnessEvalution pop;
	Reproduction repro;
	Crossover cross;
	Mutation mutation;
	Point2D p;
	ArrayList<Point2D> coord = new ArrayList<Point2D>();
	String[][] routes;
	String[][] generations;
	
	int pop_size;
	int k;
	
	//String[] test;
	Random rand = new Random();
	int index;
	int check;
	
	// from reproduction class
	String[] parents;
	
	// cross over
	String[][] children;
	double cross_rate;
	boolean check_cross = false;
	
	
	//mutation class
	double mutation_rate;
	String[][] mut_children;
	String[][] mut_parents;
	boolean check_mutation = false;
	
	
	//elitism
	String[] elite_parents = new String[2];
	
	//Scanner
	Scanner sc;
	
	// boolean check if crossover runs or not 
	
	
	public Mainclass() throws FileNotFoundException{
		
		
		
		
		int random_seed;
		
		file_reader("berlin52.txt");
		pop = new FitnessEvalution();
		repro = new Reproduction();
		cross = new Crossover();
		mutation = new Mutation();
	
		// inputs
		sc = new Scanner(System.in);
		//enter random seed 
		
		System.out.println("Enter a random seed");
		random_seed = sc.nextInt();
		rand.setSeed(random_seed);
		
		System.out.println("Enter the population size: ");
		pop_size = sc.nextInt();
		
		// k = selection for best fitness
		System.out.println("Enter the value of k, min of 2 and max of 5: ");
		k = sc.nextInt();
		
		// cross rate
		System.out.println("Enter the crossover rate between 0 to 1: ");
		cross_rate = sc.nextDouble();
		cross_rate = Math.round(cross_rate*100.0) / 100.0;
		
		//mutation rate
		System.out.println("Enter the mutation rate between 0 to 1: ");
		mutation_rate = sc.nextDouble();
		mutation_rate = Math.round(mutation_rate*100.0)/100.0;
		
		
		
		
		routes(pop_size);
		// causes null pointer exception
		generations = routes;
		
		
		int elite_percent = (int) Math.round((pop_size * 0.1));
		//System.out.println(elite_percent);
		String[][] new_array = new String[pop_size][generations[0].length];
		
		//System.out.println(generations.length);
		System.out.println("Enter the amount of generation");
		
		
		int Max_gen = sc.nextInt();
		
		try {
		   PrintStream out = new PrintStream(new FileOutputStream("unfi_5a_5.txt"));
		   System.setOut(out);
		  } catch (FileNotFoundException e1) {
		   e1.printStackTrace();
		  }
		
		
		for(int i=0; i<Max_gen; i++){
			// selection 
			//System.out.println(generations.length);
			int p;
			// new array 
			// fill it with children or parent depending on crossover or mutation
			
			//have a for loop that runs pop_size 
			// 2 parents two new child put new generations.
			
			
			
			for(int pop=0; pop<pop_size-1; pop++){
				
				for(int pt=0; pt<2; pt++){	
					repro.Initailize_arrays(coord, generations, k);
					repro.min_values(pt);
				}
				parents = repro.get_fittest();
				
				for(int j=0; j<mut_parents.length; j++){
					p = Integer.parseInt(parents[j]);
					for(int ka=0; ka<mut_parents[j].length; ka++){
						mut_parents[j][ka] = generations[p][ka];
					}
				}
				
				if( 1-cross_rate > 0.2){
					cross.Initailize_crossover(parents, generations);
					//cross.print();
					children = cross.get_children();
					check_cross = true;
				}
				
				if( 1-mutation_rate > 0.5){
					if(check_cross == true){
						mutation.Initialize(children, coord.size());
					}else{
						mutation.Initialize(mut_parents, coord.size());
					}
					
					mut_children = mutation.get_mut_children();
					check_mutation = true;
				}
				
				// cross rate
				if(check_cross == true){
					if(children != null){
						for(int b=0; b<new_array[pop].length; b++){
							new_array[pop][b] = children[0][b];
							new_array[pop+1][b] = children[1][b];
						}
					}
				}
				
				//mutation
				if(check_mutation == true){
					if(mut_children != null){
						for(int b=0; b<new_array[pop].length; b++){
							new_array[pop][b] = mut_children[0][b];
							new_array[pop+1][b] = mut_children[1][b];
						}
					}
					
				}
			
			}
				
			// elitism in process. stay tuned	
			
			
				generations = new_array;
				// average of best fit
				
				average(generations, pop_size);
			}
	}
			
	/**
	 * 
	 */
	
	
	
	
	/**
	 * method reades a file and stores in in array list
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public void file_reader(String fileName) throws FileNotFoundException{
		@SuppressWarnings("resource")
		Scanner FileScanner = new Scanner(new File(fileName));
		int i=0;
		while(FileScanner.hasNextLine()){
			if(FileScanner.hasNextDouble()){
//				System.out.println(FileScanner.nextDouble() + " " + FileScanner.nextDouble());
				p = new Point2D.Double(FileScanner.nextDouble(), FileScanner.nextDouble());
				coord.add(i, p);
				i++;
			}else{
				break;
			}
		}
	}
	
	public void routes(int pop_size){
		routes = new String[pop_size][coord.size()];
		mut_parents = new String[2][coord.size()];
		for(int i=0; i<routes.length; i++){
			// pick random 52 indexs and append that to string 
			// add it to routes as a combination 
			// index cannot repeat themselves
			routes[i] = routes_comb(coord.size());
		}
	}
	
	
	
	/**
	 * makes routes of cities, so city can only be visit onces
	 * @return
	 */
	public String[] routes_comb(int pop_size){
		String[] combinations = new String[pop_size];
		String str_index;
		boolean checking;
		for(int j=0; j<combinations.length; ){
			index = rand.nextInt(coord.size());
			str_index = Integer.toString(index);
			checking = check(combinations, str_index);
			if(checking == true){
				
			}else{
				combinations[j] = str_index;
				j++;
			}
		}
		return combinations;
	}
	
	/**
	 * Checks if array already holdes a city
	 * @param combinations
	 * @param index
	 * @return
	 */
	public boolean check(String[] combinations, String index){
		List<String> list = Arrays.asList(combinations);
		if(list.contains(index)){
			return true;
		}
		return false;
	}
	
	
	/**
	 * This function will find the average of each generations
	 * @param args
	 * @throws FileNotFoundException
	 */
	public void average(String[][] generations, int pop_size){
		double average; 
		double total = 0;
		double best_fit_value = 0;
		
		for(int i=0; i<generations.length; i++){
			total += pop.getDistance(coord, generations, i);
			best_fit_value = pop.getDistance(coord, generations, i);
		}
		
		average = total / pop_size;
		
		System.out.println(best_fit_value);
		System.out.println(average);
		
	}
	public static void main(String[] args) throws FileNotFoundException{
		new Mainclass();
	}
}
