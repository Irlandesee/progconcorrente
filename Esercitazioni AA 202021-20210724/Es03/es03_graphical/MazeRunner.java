
public class MazeRunner extends Thread{

	private static Labyrinth labyrinth;
	
	// Coordinates of the running cell
	private int x;
	private int y;
	
	public MazeRunner(int x, int y) {
		
		this.x = x;
		this.y = y;
	}

	public static void setLabyrinth(Labyrinth lab) {
		labyrinth = lab;
	}
	
	public void run() {

		// Quit on the borders of the labyrith
		if (x==0 || y==0 || x==labyrinth.getSize()+1 || y==labyrinth.getSize()+1) return;
		
		// Quit if someone has visited the cell
		if (labyrinth.isVisited(x, y)) return;
		
		// Draw my position on the labyrinth
		labyrinth.visit(x, y);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show();
        StdDraw.pause(30);

		// Quit if someone has solved the labyrinth
		if (labyrinth.isDone()) return;

        if (labyrinth.toNorth(x, y)) {
        	new MazeRunner(x, y + 1).start();
        }
        if (labyrinth.toEast(x, y)) {
        	new MazeRunner(x + 1, y).start();
        }
        if (labyrinth.toSouth(x, y)) {
        	new MazeRunner(x, y - 1).start();
        }
        if (labyrinth.toWest(x, y)) {
        	new MazeRunner(x - 1,y).start();
        }
        
        /*
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show();
        StdDraw.pause(30);
        */

	
	}
}
