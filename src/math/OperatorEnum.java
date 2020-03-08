package math;

import java.util.Arrays;

public enum OperatorEnum implements Operator{
  ADD("+", "+"),
  SUB("-", "-"),
  MUL("*", "×"),
  DIV("/", "÷");
  
  private String operator;
  private String mathOperator;
  
  OperatorEnum(String operator, String mathOperator) {
    this.operator = operator;
    this.mathOperator = mathOperator;
  }

  public String getOperator() {
    return operator;
  }

  public String getMathOperator() {
    return mathOperator;
  }

	public String toString() {
		return "" + operator;
	}
  
  public static Operator retrieveOperator(String key) {
  	System.out.println("key="+key);
  	return Arrays.asList(OperatorEnum.values()).stream()
  			.filter( op -> (op.getOperator().equals(key) || op.getMathOperator().equals(key)))
  			.findFirst().get();
  }
}
