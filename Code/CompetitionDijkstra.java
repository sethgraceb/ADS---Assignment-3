//Author: Seth Grace Banaga with the help of Sharleen Bang finding the time required for the competition
//        Credit also goes to https://algs4.cs.princeton.edu/44sp/DijkstraSP.java.html for pieces of their code
//		  used in the constructor

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
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {
	private double[] distTo;
	private DirectedEdge[] edgeTo;
	private DirectedEdge[] newEdge;
	private IndexMinPQ<Double> pq;
	private Bag<DirectedEdge>[] adj;
	private double[] distance;
	
	private double[][] total;
	
	private int speedA;
	private int speedB;
	private int speedC;
	
	private int totalVertices;
	private int totalEdges;
	
    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */
    
	CompetitionDijkstra (String filename, int sA, int sB, int sC){
		speedA = sA;
		speedB = sB;
		speedC = sC;

		try {
			double[] theFile = new In(filename).readAllDoubles();
			totalVertices = (int) theFile[0];
			totalEdges = (int) theFile[1];

			adj = (Bag<DirectedEdge>[]) new Bag[totalVertices];
			pq = new IndexMinPQ<Double>(totalVertices);
			distTo = new double[totalVertices];
			edgeTo = new DirectedEdge[totalEdges];
			newEdge = new DirectedEdge[totalVertices];
			total = new double[totalVertices][totalVertices];

			for (int v = 0; v < totalVertices; v++) {
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
		// dijkstra
		for (int i = 0; i < this.totalVertices; i++) {
			for (int v = 0; v < this.totalVertices; v++) {
				distTo[v] = Double.POSITIVE_INFINITY;
				distTo[i] = 0.0;
			}
			this.pq.insert(i, distTo[i]);
			while (!pq.isEmpty()) {
				int v = pq.delMin();
				for (DirectedEdge e : adj[v])
					relax(e);
			}
			for (int j = 0; j < this.totalVertices; j++) {
				if(distTo[j] == Double.POSITIVE_INFINITY){
					distTo[j] = 0;
				}
				total[i][j] = distTo[j];
			}
		}
    }

    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            newEdge[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }
    
    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
	public int timeRequiredforCompetition() {
		distance = new double[totalVertices];
		int minimumTime = -1;
		double maximumDistance = 0.0;
		try {
			if (speedA <= 0 || speedB <= 0 || speedC <= 0 || distTo.length == 0) {
				return minimumTime;
			}
		} catch (NullPointerException e) {
			return -1;
		}
		int tmpI = 0;
		int tmpJ = 0;
		for (int i = 0; i < totalVertices; i++) {
			for (int j = 0; j < totalVertices; j++) {

				if (total[i][j] == 0.0) {
					continue;
				} else if (total[i][j] > maximumDistance) {
					tmpI = i;
					tmpJ = j;
					maximumDistance = total[i][j];
				}
			}
		}
		if(total[tmpJ][tmpI] == 0.0){
			return -1;
		}
		else{
		int slowestSpeed = Math.min(this.speedA, this.speedB);
		slowestSpeed = Math.min(slowestSpeed, this.speedC);
		minimumTime = (int) Math.ceil((maximumDistance * 1000 / slowestSpeed));
		return minimumTime;
		}
	}

}