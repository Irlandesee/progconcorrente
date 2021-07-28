
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel
                         implements ActionListener {
  private static final long serialVersionUID = 1L;
  JButton yellowButton;
  JButton blueButton;
  JButton redButton;
  public ButtonPanel() {
    yellowButton = new JButton("Yellow");
    blueButton = new JButton("Blue");
    redButton = new JButton("Red");
    add(yellowButton);
    add(blueButton);
    add(redButton);
    yellowButton.addActionListener(this);
    blueButton.addActionListener(this);
    redButton.addActionListener(this);
  }
  public void actionPerformed(ActionEvent evt) {
    Object source = evt.getSource();
    Color color = getBackground();
    if(source.equals(yellowButton))
      color = Color.yellow;
    else if (source.equals(blueButton))
      color = Color.blue;
    else if (source.equals(redButton))
      color = Color.red;
    this.setBackground(color);
    this.repaint();
  }
}

