package math;

import java.util.Arrays;

public enum OperatorEnum implements Operator{
  ADD('+', '+'),
  SUB('-', '-'),
  MUL('*', '×'),
  DIV('/', '÷');
  
  private char operator;
  private char mathOperator;
  
  OperatorEnum(char operator, char mathOperator) {
    this.operator = operator;
    this.mathOperator = mathOperator;
  }

  public char getOperator() {
    return operator;
  }

  public char getMathOperator() {
    return mathOperator;
  }

	public String toString() {
		return "" + operator;
	}
  
  public static Operator retrieveOperator(char key) {
  	return Arrays.asList(OperatorEnum.values()).stream()
  			.filter( op -> (op.getOperator()==key)).findFirst().get();
  }
}
