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
		int nextRow=row;
		int nextCol=col;
		while(!lab.isVisited(nextRow,nextCol) && !lab.isDone()) {
			col=nextCol; row=nextRow;
			System.out.println("solver "+this.getName()+" at cell ("+col+", "+row+")");
			lab.visit(row,col);
			if(!lab.isDone()) {
				boolean nextStep=false;
				if(lab.toNorth(row,col) && !lab.isVisited(row-1, col)) {
					System.out.println("solver "+this.getName()+" to north ("+col+", "+(row-1)+")");
					nextRow=row-1;
					nextStep=true;
				}
				if(lab.toSouth(row,col) && !lab.isVisited(row+1, col)) {
					if(nextStep){
						new MazeSolver(lab,row+1, col).start();
					} else {
						System.out.println("solver "+this.getName()+" to south ("+col+", "+(row+1)+")");
						nextRow=row+1;
						nextStep=true;
					}
				}
				if(lab.toWest(row,col) && !lab.isVisited(row, col-1)) {
					if(nextStep){
						new MazeSolver(lab,row,col-1).start();
					} else {
						System.out.println("solver "+this.getName()+" to west ("+(col-1)+", "+row+")");
						nextCol=col-1;
						nextStep=true;
					}
				}
				if(lab.toEast(row,col) && !lab.isVisited(row, col+1)) {
					if(nextStep){
						new MazeSolver(lab,row,col+1).start();
					} else {
						System.out.println("solver "+this.getName()+" to east ("+(col+1)+", "+row+")");
						nextCol=col+1;
					}
				}			
			}
		}
	}
}