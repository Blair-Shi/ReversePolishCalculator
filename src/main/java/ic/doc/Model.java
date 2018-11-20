package ic.doc;

import java.util.Stack;

public class Model {
  public int result;
  public String expressionString = "";
  Stack<Integer> stack = new Stack<>();
  private Updatebale view;
  public boolean isValid = true;

  Model(Updatebale view) {
    this.view = view;
  }

  private void addNumber(int n) {
    expressionString += n + " ";
    isValid = true;
    stack.add(n);
    view.update(this);
  }

  private void evaluate(char op) {
    if (stack.size() < 2) {
      isValid = false;
      view.update(this);
      return;
    }
    expressionString += op + " ";
    isValid = true;
    int y = stack.pop();
    int x = stack.pop();
    switch (op) {
      case '+':
        result = x + y;
        break;
      case '-':
        result = x - y;
        break;
      case '*':
        result = x * y;
        break;
      case '/': // which does integer division
        result = x / y;
        break;
      default:
        break;
    }
    stack.add(result);
    view.update(this);
  }

  public void reset() {
    stack.clear();
    isValid = true;
    result = 0;
    expressionString = "";
    view.update(this);
  }

  public void addArgument(char c) {
    if (0 <= c - '0' && c - '0' <= 9) {
      addNumber(c - '0');
    } else {
      evaluate(c);
    }
  }
}
