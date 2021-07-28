public class Labyrinth{

	private int rows;
	private int cols;


	private static boolean[][] north = {
		{}
		{}
	};
	private static boolean[][] east;
	private static boolean[][] south;
	private static boolean[][] west;

	private static boolean[][] visited;

	private boolean done = false;

	public Labyrinth(){

	}

	public void clear(){

	}

	public int getNumColumns(){
		return cols;
	}

	public int getNumRows(){
		return rows;
	}

	public bollean isVisited(itn r, int c){
		return visited[r][c];
	}

	public synchronized void visit(int r, int c){
		visited[r][c] = true;
		if(c == cols-1 && r == rows-1){
			System.out.println("Visiting exit");
			done = true;
		}
	}

	public boolean isDone(){
		return done;
	}

	public boolean toNorth(int r, int c){
		return north[r][c];
	}

	public boolean toEast(int r, int c){
		return east[r][c];
	}

	public boolean toSouth(int r, int c){
		return south[r][c];
	}

	public boolean toWest(int r, int c){
		return west[r][c];
	}

}