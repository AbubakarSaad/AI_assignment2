package ga;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author abubakarsaad
 *
 */
public class FitnessEvalution {

	// routes = poplutions
	// coord = stores coordinates in there;
	//pick k random routes
	// find out the total distance
	// also a distance from last city to first city
	double distance;
	int index;
	int duplicates;
	
	public FitnessEvalution(){
		
		
	}
	/**
	 * This method calculates the distance between the points for one given index
	 * @param coord - point2d array
	 * @param routes - 
	 */
	public double getDistance(ArrayList<Point2D> coord, String[][] routes, int k){
		distance = 0;
		int point_1, point_2;
		double x_1, y_1, x_2, y_2;
		index = k;
		
		// for test purpose the random is not used, replace the 0 with the random
       //		System.out.println(random);
		for(int j=0; j<routes[index].length; j++){
//			System.out.print(routes[random][j] + " ");
			point_1 = Integer.parseInt(routes[index][j]);
			if(j < routes[index].length-1){
				point_2 = Integer.parseInt(routes[index][j+1]);
			}else{
				point_2 = Integer.parseInt(routes[index][0]);
			}
			x_1 = coord.get(point_1).getX();
			y_1 = coord.get(point_1).getY();
			x_2 = coord.get(point_2).getX();
			y_2 = coord.get(point_2).getY();
			// finds a point
			distance += Math.sqrt(Math.pow((x_2-x_1), 2) + Math.pow((y_2-y_1),2));
		}
		return distance;
	}
	
	
	
}
