public class Table {
  private static final int NUM_PHIL = 5;
  public static void main(String[] args) {
    Chopstick[] sticks = new Chopstick[NUM_PHIL];
    for(int i=0; i<NUM_PHIL; i++)
       sticks[i]=new Chopstick(i+1);
    for(int i=0; i<NUM_PHIL; i++)
       new Philosopher("F"+(i+1), sticks[i],
                         sticks[(i+1)%NUM_PHIL]).start();
  }
}
