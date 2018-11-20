package ic.doc;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class View implements Updatebale {

  private final List<JButton> buttons = Arrays.asList(
      new JButton("0"),
      new JButton("1"),
      new JButton("2"),
      new JButton("3"),
      new JButton("4"),
      new JButton("5"),
      new JButton("6"),
      new JButton("7"),
      new JButton("8"),
      new JButton("9"),
      new JButton("+"),
      new JButton("-"),
      new JButton("*"),
      new JButton("/"),
      new JButton("Reset")
  );
  private final JTextField textField = new JTextField(5);
  private final JTextField expressionField = new JTextField(20);

  public View(ActionListener controller) {
    System.setProperty("java.awt.headless", "true");
    JFrame frame = new JFrame("Reversed Polish Calculator");
    frame.setSize(200, 300);

    for (JButton button: buttons) {
      button.addActionListener(controller);
    }

    JPanel panel = new JPanel();
    for (JButton button: buttons) {
      panel.add(button);
    }
    panel.add(expressionField);
    panel.add(textField);
    frame.getContentPane().add(panel);
    frame.setVisible(true);
  }

  @Override
  public void update(Model model) {
    if (model.isValid) {
      textField.setText(String.valueOf(model.result));
      expressionField.setText(model.expressionString);
    } else {
      textField.setText("Invalid Input");
    }
  }
}
