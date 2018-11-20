package ic.doc;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class ModelTest {

  private final Updatebale updatebale = new Updatebale() {
    @Override
    public void update(Model model) {
      return;
    }
  };

  private final Model model = new Model(updatebale);
  String evaluable1 = "1 2 +";
  String evaluable2 = "3 4 -";
  String evaluable3 = "5 6 *";
  String evaluable4 = "7 8 /";
  String evaluable5 = "3 4 2 + 3 4 - 4 / 9 4 * + + +";
  String invalid1 = "+";
  String invalid2 = "2 /";
  String invalid3 = "3 4 + *";

  @Test
  public void evaluateOnePlusTwoEqualToThree() {
    List<String> args = Arrays.asList(evaluable1.split(" "));
    for (String arg: args) {
      char c = arg.charAt(0);
      model.addArgument(c);
    }
    assertThat(model.result, is(3));
    assertThat(model.isValid, is(true));
  }

  @Test
  public void evaluateThreeMinusFourEqualToMinusOne() {
    List<String> args = Arrays.asList(evaluable2.split(" "));
    for (String arg: args) {
      char c = arg.charAt(0);
      model.addArgument(c);
    }
    assertThat(model.result, is(-1));
    assertThat(model.isValid, is(true));
  }

  @Test
  public void evaluateFiveTimesSixEqualToThirty() {
    List<String> args = Arrays.asList(evaluable3.split(" "));
    for (String arg: args) {
      char c = arg.charAt(0);
      model.addArgument(c);
    }
    assertThat(model.result, is(30));
    assertThat(model.isValid, is(true));
  }

  @Test
  public void evaluateSevenOverEightEqualZero() {
    List<String> args = Arrays.asList(evaluable4.split(" "));
    for (String arg: args) {
      char c = arg.charAt(0);
      model.addArgument(c);
    }
    assertThat(model.result, is(0));
    assertThat(model.isValid, is(true));
  }

  @Test
  public void evaluateLongValidExpression() {
    List<String> args = Arrays.asList(evaluable5.split(" "));
    for (String arg: args) {
      char c = arg.charAt(0);
      model.addArgument(c);
    }
    assertThat(model.result, is(45));
    assertThat(model.isValid, is(true));
  }

  @Test
  public void evaluatePlusWhenNoNumberInStackCauseInvalidInput() {
    List<String> args = Arrays.asList(invalid1.split(" "));
    for (String arg: args) {
      char c = arg.charAt(0);
      model.addArgument(c);
    }
    assertThat(model.isValid, is(false));
  }

  @Test
  public void evaluateBinaryOperationWhenOneNumberInStackCauseInvalidInput() {
    List<String> args = Arrays.asList(invalid2.split(" "));
    for (String arg: args) {
      char c = arg.charAt(0);
      model.addArgument(c);
    }
    assertThat(model.isValid, is(false));
  }

  @Test
  public void evaluateInvalidInputPreservePreviousResult() {
    List<String> args = Arrays.asList(invalid3.split(" "));
    for (String arg: args) {
      char c = arg.charAt(0);
      model.addArgument(c);
    }
    assertThat(model.result, is(7));
    assertThat(model.isValid, is(false));
  }

  @Test
  public void cleanMemoryWhenRest() {
    List<String> args = Arrays.asList(evaluable3.split(" "));
    for (String arg: args) {
      char c = arg.charAt(0);
      model.addArgument(c);
    }
    model.reset();
    assertThat(model.result, is(0));
    assertThat(model.isValid, is(true));
    assertThat(model.expressionString, is(""));
  }
}
