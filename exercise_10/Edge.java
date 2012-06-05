package exercise_10;

public class Edge {
	private int weight;
	private Vertex first;
	private Vertex second;
	
	public Edge(int weight, Vertex first, Vertex second) {
		this.weight = weight;
		this.first = first;
		this.second = second;
	}

	public int getWeight() {
		return weight;
	}

	public Vertex getFirst() {
		return first;
	}

	public Vertex getSecond() {
		return second;
	}	
}
