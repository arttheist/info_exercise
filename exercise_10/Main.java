package exercise_10;

public class Main {

	public static void main(String[] args) {
		RandomGraph r = new RandomGraph(20, 45);
		try {
			System.out.println(r.getGraph().getCheapestPath("AA", "AC").pathToString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
