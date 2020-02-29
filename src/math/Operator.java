package math;

import java.util.Arrays;

/**
 * Created by liangzhang on 2019/6/13.
 */
public class Operator {
  private char operator;
  private char mathOperator;

  Operator(char operator, char mathOperator) {
    this.operator = operator;
    this.mathOperator = mathOperator;
  }

  public char getOperator() {
    return operator;
  }

  public char getMathOperator() {
    return mathOperator;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Operator))
      return false;

    Operator operator1 = (Operator) o;

    if (operator != operator1.operator)
      return false;
    return mathOperator == operator1.mathOperator;

  }

  @Override
  public String toString() {
    return "Operator{" +
        "operator=" + operator +
        ", mathOperator=" + mathOperator +
        '}';
  }
}
