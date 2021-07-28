import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
class ButtonFrame extends JFrame {
  private static final long serialVersionUID = 1L;
  public ButtonFrame() {
    setTitle("ButtonTest"); setSize(300, 200);
    addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent e) {
             System.exit(0);
           }
    });
    Container contentPane = getContentPane();
    contentPane.add(new ButtonPanel());
  }
  public static void main(String[] args) {
    JFrame frame = new ButtonFrame();
    frame.setVisible(true);
  }
}

