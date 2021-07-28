
public class InputHandler implements Observer {
  private String info;
  public void update(Observable o, Object arg){
    this.info = (String) arg;
    System.out.println(info + " communicated");
  }
}
