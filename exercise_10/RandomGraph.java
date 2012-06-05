package exercise_10;

import java.util.*;

public class RandomGraph {
	
	private WeightedGraph mightyGraph;
	
	public RandomGraph(int numberOfVertex, int numberOfEdges) {
		
		for (int i=0;i<numberOfVertex;i++) {
			char numbers = 'A';
			String vertexNumbers = Character.toString(numbers);
			mightyGraph.addVertex(vertexNumbers);
			numbers++;
		}
		
		Random generator = new Random();
		
		for (int i = 0; i<numberOfEdges;i++) {
			int rdm1 = generator.nextInt(mightyGraph.getAllVertices().size())+1;
			int rdm2 = generator.nextInt(mightyGraph.getAllVertices().size())+1;
			
			while (rdm1 == rdm2)
					rdm2 = generator.nextInt(mightyGraph.getAllVertices().size())+1;
			
			String firstVertex = mightyGraph.getAllVertices().get(rdm1).getName();
			String secondVertex = mightyGraph.getAllVertices().get(rdm2).getName();
			int randomWeight = generator.nextInt( Integer.MAX_VALUE ) + 1;
			mightyGraph.addEdge(firstVertex,secondVertex,randomWeight);
		}
		
	}
	
}
