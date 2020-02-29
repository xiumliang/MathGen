package math;

/**
 * Created by liangzhang on 2019/6/13.
 */
public class MathFomular {
	
  private String firstNum;
  private String secondNum;
  private String Operator;  
  
	public void setFirstNum(String firstNum) {
		this.firstNum = firstNum;
	}

	public void setSecondNum(String secondNum) {
		this.secondNum = secondNum;
	}

	public void setOperator(String operator) {
		Operator = operator;
	}

	public String getFirstNum() {
    return firstNum;
  }

  public String getSecondNum() {
    return secondNum;
  }
  
  public int getIntFirstNum() {
    return Integer.parseInt(firstNum);
  }

  public int getIntSecondNum() {
    return Integer.parseInt(secondNum);
  }

  public String getOperator() {
    return Operator;
  }

  @Override
  public String toString() {
    return "MathFomular{" +
        firstNum + Operator + secondNum +
        '}';
  }

	public String toFormularString() {
		return firstNum + Operator + secondNum;
	}

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return false;
    if (!(o instanceof MathFomular))
      return false;

    MathFomular that = (MathFomular) o;

    if (!firstNum.equals(that.firstNum))
      return false;
    if (!secondNum.equals(that.secondNum))
      return false;
    return Operator.equals(that.Operator);

  }

  @Override
  public int hashCode() {
    int result = firstNum.hashCode();
    result = 31 * result + secondNum.hashCode();
    result = 31 * result + Operator.hashCode();
    return result;
  }
}
