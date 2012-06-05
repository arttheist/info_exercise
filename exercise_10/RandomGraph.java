package exercise_10;

public class RandomGraph {
	
	private WeightedGraph mightyGraph;
	
	public RandomGraph(int numberOfVertex, int numberOfEdges) {
		
		for (int i=0;i<numberOfVertex;i++) {
			char numbers = 'A';
			String vertexNumbers = Character.toString(numbers);
			mightyGraph.addVertex(vertexNumbers);
			numbers++;
		}
		
		for (int i = 0; i<numberOfEdges;i++) {
			
		}
		
	}
	
}
