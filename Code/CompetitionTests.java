import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class CompetitionTests {

	@Test
	public void testDijkstraConstructor() {
		String textA = "input-A.txt";
		String textB = "input-B.txt";
		String textC = "input-C.txt";
		String textD = "input-D.txt";
		String textF = "input-F.txt";
		String textK = "input-K.txt";
		String textG = "input-G.txt";
		String textI = "input-I.txt";
		String text = "tinyEWD.txt";
		String textUnknown = "nsjdfnsf.txt";
		
		CompetitionDijkstra testA = new CompetitionDijkstra(textA, 30, 60, 25);
		assertEquals(-1, testA.timeRequiredforCompetition());
		
		CompetitionDijkstra testA2 = new CompetitionDijkstra(textA, -2, -6, -5);
		assertEquals(-1, testA2.timeRequiredforCompetition());
		
		CompetitionDijkstra testB = new CompetitionDijkstra(textB, 60, 80, 50);
		assertEquals(10000, testB.timeRequiredforCompetition());
		
		CompetitionDijkstra testC = new CompetitionDijkstra(textC, 7, 5000, 89);
		assertEquals(-1, testC.timeRequiredforCompetition());
		
		CompetitionDijkstra testD = new CompetitionDijkstra(textD, 50, 80, 60);
		assertEquals(38, testD.timeRequiredforCompetition());
		
		CompetitionDijkstra testF = new CompetitionDijkstra(textF, 30, 90, 50);
		assertEquals(-1, testF.timeRequiredforCompetition());
		
		CompetitionDijkstra testK = new CompetitionDijkstra(textK, 51, 7, 252846);
		assertEquals(2286, testK.timeRequiredforCompetition());
		
		CompetitionDijkstra testG = new CompetitionDijkstra(textG, 242, 72, 10567);
		assertEquals(-1, testG.timeRequiredforCompetition());
			
		CompetitionDijkstra testI = new CompetitionDijkstra(textI, 3233, 7, 2368726);
		assertEquals(1715, testI.timeRequiredforCompetition());
		
		CompetitionDijkstra testX = new CompetitionDijkstra(text, 50, 80, 60);
		assertEquals(38, testX.timeRequiredforCompetition());
		
		CompetitionDijkstra testUnknown = new CompetitionDijkstra(textUnknown, 50, 80, 60);
		assertEquals(-1, testUnknown.timeRequiredforCompetition());
		

	}

	@Test
	public void testFWConstructor() throws IOException {
		String textA = "input-A.txt";
		String textB = "input-B.txt";
		String textC = "input-C.txt";
		String textD = "input-D.txt";
		String textI = "input-I.txt";
		String textF = "input-F.txt";
		String textG = "input-G.txt";
		String textUnknown = "nsjdfnsf.txt";
		
		CompetitionFloydWarshall testA = new CompetitionFloydWarshall(textA, 25, 30, 75);
		assertEquals(-1, testA.timeRequiredforCompetition());
		
		CompetitionFloydWarshall testA2 = new CompetitionFloydWarshall(textA, -2, -12, -1);
		assertEquals(-1, testA2.timeRequiredforCompetition());
			
		CompetitionFloydWarshall testB = new CompetitionFloydWarshall(textB, 60, 80, 50);
		assertEquals(10000, testB.timeRequiredforCompetition());
		
		CompetitionFloydWarshall testC = new CompetitionFloydWarshall(textC, 5, 7000, 69);
		assertEquals(-1, testC.timeRequiredforCompetition());
		
		CompetitionFloydWarshall testD = new CompetitionFloydWarshall(textD, 50, 80, 60);
		assertEquals(38, testD.timeRequiredforCompetition());
		
		CompetitionFloydWarshall testI = new CompetitionFloydWarshall(textI, 5473, 7, 2646426);
		assertEquals(1715, testI.timeRequiredforCompetition());
				
		CompetitionFloydWarshall testF = new CompetitionFloydWarshall(textF, 50, 80, 60);
		assertEquals(-1, testF.timeRequiredforCompetition());
		
		CompetitionFloydWarshall testG = new CompetitionFloydWarshall(textG, 232, 47, 6351);
		assertEquals(-1, testG.timeRequiredforCompetition());
		
		CompetitionFloydWarshall testUnknown = new CompetitionFloydWarshall(textUnknown, 50, 80, 60);
		assertEquals(-1, testUnknown.timeRequiredforCompetition());
	}
}
