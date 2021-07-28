public class MazeSolver extends Thread{

	private int row;
	private int col;

	private Labyrinth lab;

	public MazeSolver(Labyrinth lab, int row, int col){
		this.lab = lab;
		this.row = row;
		this.col = col;
	}

	/**
	*Subottimale
	**/
	public void run(){
		if(!lab.isVisited(row, col)){
			lab.visit(row, col);
			if(!lab.isDone()){
				if(lab.toNorth(row, col)){
					new MazeSolver(lab, row-1, col).start(); 
				}
				else if(lab.toSouth(row, col)){
					new MazeSolver(lab, row+1, col).start();
				}
				else if(lab.toWest(row, col)){
					new MazeSolver(lab, row, col-1).start();
				}
				else if(lab.toEast(row, col)){
					new MazeSolver(lab, row, col+1).start();
				}
			}

		}
	}


}