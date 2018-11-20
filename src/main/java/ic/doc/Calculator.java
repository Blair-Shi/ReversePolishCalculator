package ic.doc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Calculator {
  private View view = new View(new Controller());
  private Model expressionProcessor = new Model(view);

  class Controller implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      JButton button = (JButton) e.getSource();
      if (button.getText().equals("Reset")) {
        expressionProcessor.reset();
      } else {
        char c = button.getText().charAt(0);
        expressionProcessor.addArgument(c);
      }
    }
  }

  public static void main(String[] args) {
    new Calculator();
  }
}
