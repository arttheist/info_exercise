package exercise_10;

import java.util.ArrayList;


public class GraphPath {
	ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
	ArrayList<Integer> weightList = new ArrayList<Integer>();
	
	public GraphPath(Vertex v) {
		vertexList.add(v);
	}
	
	public void addVertex(Vertex v, int w) {
		vertexList.add(v);
		weightList.add(w);
	}
	
	public String pathToString() {
		String result = "";
		for(int i = 0; i < weightList.size(); i++) {
			result += vertexList.get(i).getName() + " --" + weightList.get(i) + "-- ";
		}
		result += vertexList.get(vertexList.size() - 1);
		return result;
	}
}
