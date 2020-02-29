package math;


/**
 * Created by liangzhang on 2019/6/13.
 */
public class OperatorFactory {
  private static Operator[] validOperators = new Operator[256];
  static{
    validOperators['+'] = new Operator ('+', '+');
    validOperators['-'] = new Operator ('-', '-');
    validOperators['*'] = new Operator ('*', '×');
    validOperators['/'] = new Operator ('/', '÷');
  };

  private OperatorFactory(){};

  public static boolean checkOperator (char operator) {
    return (validOperators[operator]!=null);
  }


  public static Operator getOperatorInstance (char operator) {
    if (!checkOperator(operator))
      throw new IllegalArgumentException("错误的运算符[" + operator + "]");

    return validOperators[operator];
  }
}
