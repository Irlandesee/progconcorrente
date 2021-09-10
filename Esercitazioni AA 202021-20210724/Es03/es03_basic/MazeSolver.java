public class MazeSolver extends Thread{
	int row;
	int col;
	Labyrinth lab;
	public MazeSolver(Labyrinth l, int r, int c) {
		this.row = r;
		this.col = c;
		System.out.println("solver "+this.getName()+" created with cell coordinates ("+col+", "+row+")");
		this.lab = l;
	}
	public void run() {
		System.out.println("solver "+this.getName()+" runs with cell coordinates ("+col+", "+row+")");
		if(!lab.isVisited(row,col)) {
			lab.visit(row,col);
			if(!lab.isDone()) {
				if(lab.toNorth(row,col)) {
					System.out.println("solver "+this.getName()+" from ("+col+", "+row+") going north to ("+col+", "+(row-1)+")");
					new MazeSolver(lab, row-1, col).start();
				}
				if(lab.toSouth(row,col)) {
					System.out.println("solver "+this.getName()+" from ("+col+", "+row+") going south to ("+col+", "+(row+1)+")");
					new MazeSolver(lab,row+1, col).start();
				}
				if(lab.toWest(row,col)) {
					System.out.println("solver "+this.getName()+" from ("+col+", "+row+") going west to ("+(col-1)+", "+row+")");
					new MazeSolver(lab,row,col-1).start();
				}
				if(lab.toEast(row,col)) {
					System.out.println("solver "+this.getName()+" from ("+col+", "+row+") going east to ("+(col+1)+", "+row+")");
					new MazeSolver(lab,row,col+1).start();
				}			
			}
		}
	}
}