package exercise_10;

import java.util.ArrayList;

public class WeightedGraph {
	ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
	ArrayList<Edge> edgeList = new ArrayList<Edge>();
	
	public void addEdge(String vertexName1, String vertexName2, int weight) {
		try {
			edgeList.add(new Edge(getVertexByName(vertexName1), getVertexByName(vertexName1), weight));
		} catch (VertexNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void addVertex(String vertexName) {
		vertexList.add(new Vertex(vertexName));
	}
	
	public int getWeight(String vertexName1, String vertexName2) {
		try {
			return getEdgeByVertices(vertexName1, vertexName2).getWeight();
		} catch (EdgeNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	private Vertex getVertexByName(String vertexName) throws VertexNotFoundException {
		for(Vertex v : vertexList) {
			if(v.getName().equals(vertexName))
				return v;
		}
		throw new VertexNotFoundException();
	}
	
	private Edge getEdgeByVertices(String vertexName1, String vertexName2) throws EdgeNotFoundException {
		try {
			for(Edge e : edgeList) {
				if((e.getFirst() == getVertexByName(vertexName1) || e.getSecond() == getVertexByName(vertexName1)) && (e.getFirst() == getVertexByName(vertexName2) || e.getSecond() == getVertexByName(vertexName2)) )
					return e;
			}
		} catch (VertexNotFoundException e) {

			e.printStackTrace();
		}
		throw new EdgeNotFoundException();
	}
	
	public ArrayList<Vertex> getAllVertices() {
		return vertexList;
	}
	
	public GraphPath getCheapestPath(String vertexName1, String vertexName2) throws VertexNotFoundException, EdgeNotFoundException {
		// Set distance of all vertices to infinity (-1)
		int[] distances = new int[vertexList.size()];
		for(int i : distances)
			distances[i] = -1;
		
		//Set distance of start vertex to 0
		distances[vertexList.indexOf(getVertexByName(vertexName1))] = 0;
		
		//store previousVertices, if Vertex is visited and how much vertices are visited
		Vertex[] previousVertices = new Vertex[vertexList.size()];
		boolean[] visited = new boolean[vertexList.size()];
		int visitedVertices = vertexList.size();
		
		//While unvisited Vertices left
		while(visitedVertices > 0) {
			//get vertex with minimal distance
			Vertex nearestVertex = new Vertex("default");
			int nearestDistance = -1;
			for(Vertex v : vertexList)
				if(distances[vertexList.indexOf(v)] < nearestDistance || nearestDistance == -1) {
					nearestVertex = v;
					nearestDistance = distances[vertexList.indexOf(v)];
				}
			Vertex v = nearestVertex;
			//set v to visited
			visited[vertexList.indexOf(v)] = true;
			visitedVertices--;
			
			//get all unvisited neighbours
			ArrayList<Vertex> unvisitedNeighbours = new ArrayList<Vertex>();
			for(Vertex n : getNeighbours(v))
				if(!visited[vertexList.indexOf(n)])
						unvisitedNeighbours.add(n);
			for(Vertex n : unvisitedNeighbours) {
				// distance of neighbour
				int d = distances[vertexList.indexOf(n)];
				// distance between n and v
				int dBetween = getEdgeByVertices(v.getName(), n.getName()).getWeight();
				// if distance of the neighbour is greater than distance of v plus weight of the edge between
				if(d == -1 || d > distances[vertexList.indexOf(v)] + dBetween) {
					// set distance of neighbour to distance of v plus weight of the edge between
					distances[vertexList.indexOf(n)] = distances[vertexList.indexOf(v)] + dBetween;
					// set neighbours previous Vertex to v
					previousVertices[vertexList.indexOf(n)] = v;
				}
			}
		}
		GraphPath cheapestPath = new GraphPath(getVertexByName(vertexName2));
		Vertex v = getVertexByName(vertexName2);
		while(previousVertices[vertexList.indexOf(v)] != null) {
			cheapestPath.addVertex(previousVertices[vertexList.indexOf(v)], getWeight(v.getName(), previousVertices[vertexList.indexOf(v)].getName()));
			v = previousVertices[vertexList.indexOf(v)];
		}
		return cheapestPath;
	}
	
	private ArrayList<Vertex> getNeighbours(Vertex v) {
		ArrayList<Vertex> neighbours = new ArrayList<Vertex>();
		for(Edge e : edgeList) {
			if(e.getFirst() == v)
				neighbours.add(e.getSecond());
			else
				neighbours.add(e.getFirst());
		}
		return neighbours;
	}
	
}
