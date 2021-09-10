
public class ParLabTest {

    public static void main(String[] args) {
        Labyrinth lab = new Labyrinth();
    	lab.clear();
        new MazeSolver(lab, 0, 0).start();     
    }
}