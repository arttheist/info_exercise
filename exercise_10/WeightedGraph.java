package exercise_10;

import java.util.ArrayList;

public class WeightedGraph {
	ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
	ArrayList<Edge> edgeList = new ArrayList<Edge>();
	
	public void addEdge(String vertexName1, String vertexName2, int weight) {
		edgeList.add(new Edge(getVertexByName(vertexName1), getVertexByName(vertexName1), weight));
	}
	
	public void addVertex(String vertexName) {
		vertexList.add(new Vertex(vertexName));
	}
	
	public int getWeight(String vertexName1, String vertexName2) {
		return 
	}
	
	private Vertex getVertexByName(String vertexName) throws VertexNotFoundException{
		for(Vertex v : vertexList) {
			if(v.getName().equals(vertexName))
				return v;
		}
		throw new VertexNotFoundException();
	}
	
	private Edge getEdgeByVertices(String vertexName1, String vertexName2) throws EdgeNotFoundException {
		for(Edge e : edgeList) {
			if((e.getFirst() == getVertexByName(vertexName1) || e.getSecond() == getVertexByName(vertexName1)) && (e.getFirst() == getVertexByName(vertexName2) || e.getSecond() == getVertexByName(vertexName2)) )
				return e;
		}
		throw new EdgeNotFoundException();
	}
	
}
