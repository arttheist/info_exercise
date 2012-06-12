package exercise_10;

public class RandomGraph {
	
	private WeightedGraph mightyGraph;
	
	public RandomGraph(int numberOfVertex, int numberOfEdges) {
		
		for (int i=0;i<numberOfVertex;i++) {
			int numbers;
			char numbers1 = 'A';
			char numbers2 = 'A';
			String vertexNumbers = Character.toString(numbers);
			mightyGraph.addVertex(vertexNumbers);
			numbers++;
			
			String vertexName = Character.toString(numbers1)+Character.toString(numbers2);
			mightyGraph.addVertex(vertexName);
			
		
			if (numbers2=='Z') {
				numbers1++;
				numbers2='A';
			}
			
			numbers2++;
		}
		
		for (int i = 0; i<numberOfEdges;i++) {
			
		}
		
	}
	
}
