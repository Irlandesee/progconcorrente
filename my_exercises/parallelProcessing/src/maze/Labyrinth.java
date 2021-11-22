package maze;

public class Labyrinth {
	private int rows;
	private int cols;
	// questo e` il labirinto raffigurato nelle slide
	private static boolean north[][]= // is there an exit to north of cell i,j
		{
		{ false, false, false, false, false, false, false, false, false, false},
		{ true, false, true, true, false, true, true, false, false, true },
		{ true, false, false, false, true, false, true, true, true, false },
		{ false, true, true, false, true, true, false, false, true, true},
		{ true, false, false, true, true, false, false, true, false, false}
		} ;
	private static boolean[][] east=
		{
		{ true, true, false, true, true, true, true, true, true, false},
		{ false, true, false, true, false, true, false, false, true, false},
		{ true, false, true, false, true, false, true, false, false, false},
		{ true, true, false, true, false, true, false, true, true, false},
		{ true, true, true, false, true, false, true, true, true, false}
		}	;
	private static boolean[][] south=
		{
		{ true, false, true, true, false, true, true, false, false, true},
		{ true, false, false, false, true, false, true, true, true, false},
		{ false, true, true, false, true, true, false, false, true, true},
		{ true, false, false, true, true, false, false, true, false, false},
		{ false, false, false, false, false, false, false, false, false, false}
		}	;
	private static boolean[][] west=
		{
		{ false, true, true, false, true, true, true, true, true, true},
		{ false, false, true, false, true, false, true, false, false, true},
		{ false, true, false, true, false, true, false, true, false, false},
		{ false, true, true, false, true, false, true, false, true, true},
		{ false, true, true, true, false, true, false, true, true, true}
		}	;

	private static boolean[][] visited={
		{ false, false, false, false, false, false, false, false, false, false},
		{ false, false, false, false, false, false, false, false, false, false},
		{ false, false, false, false, false, false, false, false, false, false},
		{ false, false, false, false, false, false, false, false, false, false},
		{ false, false, false, false, false, false, false, false, false, false}

	};
	private boolean done = false;  // exit found
	public void clear(){
		System.out.println("clearing maze with rows "+rows+" and cols "+cols);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// System.out.println("Clearing "+i+" "+j);
				visited[i][j] = false;
			}
		}
	}
	public Labyrinth() {
		this.rows = north.length;
		this.cols = north[0].length;
		System.out.println("The layrinth has "+rows+" rows and "+cols+" columns");
//		visited = new boolean[rows][cols];
//		clear();
//		System.out.println("to test: south[1,0]="+south[1][0]);
	}
	public int getNumColumns(){
		return cols;
	}
	public int getNumRows(){
		return rows;
	}
	public boolean isVisited(int r, int c){
		return visited[r][c];
	}
	public synchronized void visit(int r, int c){
		visited[r][c]=true;
		if(c==cols-1 && r==rows-1){
			System.out.println("visiting exit");
			done=true;
		}
	}
	public  boolean isDone(){
		return done;
	}
	public  boolean toNorth(int r, int c){
		return north[r][c];
	}
	public  boolean toEast(int r, int c){
		return east[r][c];
	}
	public  boolean toWest(int r, int c){
		return west[r][c];
	}
	public  boolean toSouth(int r, int c){
		return south[r][c];
	}
}