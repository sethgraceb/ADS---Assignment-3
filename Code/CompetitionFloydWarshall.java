//Author: Seth Grace Banaga with the help of Sharleen Bang finding the time required for the competition
//        Credit also goes to https://algs4.cs.princeton.edu/44sp/DijkstraSP.java.html for pieces of their code
//		  used in the constructor

import java.io.IOException;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {
	private double[][] distTo;         
    private DirectedEdge[] edgeTo;   
    private DirectedEdge[][] newEdge;
	private static int totalVertices;
	private static int totalEdges;
	private Bag<DirectedEdge>[] adj;
	private double[] distance;
	
	private int speedA;
	private int speedB;
	private int speedC;
	
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     * @throws IOException 
     */
    
	CompetitionFloydWarshall (String filename, int sA, int sB, int sC){
    	speedA = sA;
    	speedB = sB;
    	speedC = sC;
    	
    	try {
			double[] theFile = new In(filename).readAllDoubles();
			totalVertices = (int) theFile[0];
			totalEdges = (int) theFile[1];
			    	
			adj = (Bag<DirectedEdge>[]) new Bag[totalVertices];	
			distTo =  new double[totalVertices][totalVertices];
			edgeTo = new DirectedEdge[totalEdges];
			newEdge = new DirectedEdge[totalVertices][totalVertices];
			
			for (int v = 0; v < totalVertices; v++){
			    adj[v] = new Bag<DirectedEdge>();
			}
			int count = 0;
			for (int i = 2; i < theFile.length; i += 3) {
				edgeTo[count] = new DirectedEdge((int) theFile[i], (int) theFile[i + 1], theFile[i + 2]);
				adj[(int) theFile[i]].add(edgeTo[count]);
				count++;		
			}
		} catch (NullPointerException e1) {
			return;
		}
    	for (int v = 0; v < totalVertices; v++) {
            for (int w = 0; w < totalVertices; w++) {
                distTo[v][w] = Double.POSITIVE_INFINITY;
            }
        }
    	
    	//floyd warshall - from the book online - algs4.cs.princeton.edu
    	for (int v = 0; v < totalVertices; v++) {
            for (DirectedEdge e : adj[v]) {
                distTo[e.from()][e.to()] = e.weight();
                newEdge[e.from()][e.to()] = e;
            }
            // in case of self-loops
            if (distTo[v][v] >= 0.0) {
                distTo[v][v] = 0.0;
                newEdge[v][v] = null;
            }
        }
		for (int i = 0; i < totalVertices; i++) {
			// compute shortest paths using only 0, 1, ..., i as intermediate
			// vertices
			for (int v = 0; v < totalVertices; v++) {
				if (newEdge[v][i] == null)
					continue; // optimization
				for (int w = 0; w < totalVertices; w++) {
					if (distTo[v][w] > distTo[v][i] + distTo[i][w]) {
						distTo[v][w] = distTo[v][i] + distTo[i][w];
						newEdge[v][w] = newEdge[i][w];
					}
				}
			}
		}
		for(int i = 0; i < totalVertices; i++){
			for(int j = 0; j < totalVertices; j++){
				if(distTo[i][j] == Double.POSITIVE_INFINITY){
					distTo[i][j] = 0.0;
				}
			}
		}
	}

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
  
	public int timeRequiredforCompetition(){
    	distance = new double[totalVertices];
    	double totalDistanceTravelled = 0.0;
    	int minimumTime = -1;
      	try {
			if(speedA <= 0 || speedB <= 0 || speedC <= 0 || distTo.length == 0){
				return minimumTime;
			}
		} catch (NullPointerException e) {
			return -1;
		}
      	int tmpI = 0;
      	int tmpJ = 0;
		for (int p = 0; p < totalVertices; p++) {
			for (int i = 0; i < totalVertices; i++) {
				if (distTo[p][i] == 0.0) {
					continue;
				} else {
					if(distTo[p][i] > totalDistanceTravelled){
						tmpI = p;
						tmpJ = i;
		    			totalDistanceTravelled = distTo[p][i];
		    		}
				}
			}

		}
		if(distTo[tmpJ][tmpI] == 0.0){
			return -1;
		}
    	int slowestSpeed = Math.min(speedA, speedB);
    	slowestSpeed = Math.min(slowestSpeed, speedC);
    	minimumTime = (int) Math.ceil(totalDistanceTravelled*1000/slowestSpeed);
    	return minimumTime;
    }
}