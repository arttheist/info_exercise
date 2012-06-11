package exercise_10;

import java.util.*;

public class RandomGraph {
	
	WeightedGraph mightyGraph = new WeightedGraph();
	
	

	public RandomGraph(int numberOfVertex, int numberOfEdges) {
		
		char numbers1 = 'A';
		char numbers2 = 'A';
		
		for (int i=0;i<numberOfVertex;i++) {
			
			String vertexName = Character.toString(numbers1)+Character.toString(numbers2);
			mightyGraph.addVertex(vertexName);
			
		
			if (numbers2=='Z') {
				numbers1++;
				numbers2='A';
			}
			
			numbers2++;
		}
		
		Random generator = new Random();
		
		for (int i = 0; i<numberOfEdges;i++) {
			int rdm1 = generator.nextInt(mightyGraph.getAllVertices().size());
			int rdm2 = generator.nextInt(mightyGraph.getAllVertices().size());
			
			while (rdm1 == rdm2)
					rdm2 = generator.nextInt(mightyGraph.getAllVertices().size())+1;
			
			String firstVertex = mightyGraph.getAllVertices().get(rdm1).getName();
			String secondVertex = mightyGraph.getAllVertices().get(rdm2).getName();
			int randomWeight = generator.nextInt( Integer.MAX_VALUE ) + 1;
			mightyGraph.addEdge(firstVertex,secondVertex,randomWeight);
		}
		
	}
	
	public WeightedGraph getGraph() {
		return mightyGraph;
	}
	
}
