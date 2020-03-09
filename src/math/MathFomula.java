package math;

/**
 * Created by liangzhang on 2019/6/13.
 */
public class MathFomula {
	
  private String firstNum;
  private String secondNum;
  private Operator Operator;  
  
	public void setFirstNum(String firstNum) {
		this.firstNum = firstNum;
	}

	public void setSecondNum(String secondNum) {
		this.secondNum = secondNum;
	}

	public void setOperator(Operator operator) {
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

  public Operator getOperator() {
    return Operator;
  }

  @Override
  public String toString() {
    return "MathFomular{" +
        firstNum + Operator + secondNum +
        '}';
  }

	public String toFormularString() {
		return firstNum + Operator.getMathOperator() + secondNum;
	}

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return false;
    if (!(o instanceof MathFomula))
      return false;

    MathFomula f = (MathFomula) o;
    if (!firstNum.equals(f.firstNum))
      return false;
    if (!secondNum.equals(f.secondNum))
      return false;
    return Operator.getOperator()==f.Operator.getOperator();

  }

  @Override
  public int hashCode() {
    int result = firstNum.hashCode();
    result = 31 * result + secondNum.hashCode();
    result = 31 * result + Operator.hashCode();
    return result;
  }
}
