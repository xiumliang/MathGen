package math;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomGenerator {
	public int MAX_ADD_LIMIT = 0;
  public int MAX_SUB_LIMIT = 0;
  public int NUM_OF_EXERCISE = 0;
  public int REMOVE_ZERO_RATIO = 10;
  public int REMOVE_ONE_RATIO = 5;
  
  private static Random r = null;
  private List<Operator> operatorList = new ArrayList<Operator>();
  private int level;
  
  Set<MathFomula> mathSet = null;

  public RandomGenerator() {
    r = new Random(getSeed());
  }

  protected int nextInt(int bound) {
    return r.nextInt(bound);
  }

  private int getSeed(){
    long seed = System.currentTimeMillis();
    return (int) (seed%10000);
  }
 
	public Set<MathFomula> generate(File dest, int maxNumber, int mathCount) {
		mathSet = new HashSet(mathCount);
		MAX_ADD_LIMIT = MAX_SUB_LIMIT = maxNumber;
		for (int i = 0; i < mathCount; i++) {
			mathSet.add(generateExercise());
		}
		return mathSet;
	}

	public MathFomula generateAddition() {
		MathFomula f = new MathFomula();
		int a = r.nextInt(MAX_ADD_LIMIT + 1);
		int b = r.nextInt(MAX_ADD_LIMIT + 1 - a);

		for (int i = 0; checkRemoveRatio(i, a, b); i++) {
			a = r.nextInt(MAX_ADD_LIMIT + 1);
			b = r.nextInt(MAX_ADD_LIMIT + 1 - a);
		}

		// simply skip 1 + 1 =, because it is useless for an excercise.
		if ((a == 1) && (b == 1)) {
			for (int i = 0; checkRemoveRatio(i, a, b); i++) {
				a = r.nextInt(MAX_ADD_LIMIT + 1);
				b = r.nextInt(MAX_ADD_LIMIT + 1 - a);
			}
		}

		f.setFirstNum(String.valueOf(a));
		f.setOperator(OperatorEnum.ADD);
		f.setSecondNum(String.valueOf(b));
		return f;
	}

  public MathFomula generateSubtraction() {
  	MathFomula f = new MathFomula();
    int a = r.nextInt(MAX_SUB_LIMIT)+1;
    int b = r.nextInt(a);

    for(int i=0; checkRemoveRatio(i, a, b); i++) {
      a = r.nextInt(MAX_SUB_LIMIT)+1;
      b = r.nextInt(a);
    }

    f.setFirstNum(String.valueOf(a));
    f.setOperator(OperatorEnum.SUB);
    f.setSecondNum(String.valueOf(b));
    return f;
  }
  
  public MathFomula generateMultiplication() {
  	MathFomula f = new MathFomula();
    int a = r.nextInt(9)+1;
    int b = r.nextInt(9)+1;

    for(int i=0; checkRemoveRatio(i, a, b); i++) {
      a = r.nextInt(9)+1;
      b = r.nextInt(9)+1;
    }

    f.setFirstNum(String.valueOf(a));
    f.setOperator(OperatorEnum.MUL);
    f.setSecondNum(String.valueOf(b));
    return f;
  }
 
  public MathFomula generateExercise() {
  	MathFomula genedMath = null;
    do {
    	switch ((OperatorEnum)randomOperator()) {
    		case ADD:
    			genedMath = generateAddition();
    			break;
    		case SUB:
    			genedMath = generateSubtraction();
    			break;
    		case MUL:
    			genedMath = generateMultiplication();
    			break;
    		default :
    				System.out.println("Invalid operator!");
    	}
    } while (mathSet.contains(genedMath));
    return genedMath;
  }
  
  private Operator randomOperator() {
  	return operatorList.get(r.nextInt(operatorList.size()));
  }

  private boolean checkRemoveRatio (int i, int a, int b) {
    if (i<REMOVE_ZERO_RATIO && (a==0 || b==0))
      return true;

    if (i<REMOVE_ONE_RATIO && (a==1 || b==1))
      return true;

    return false;
  }

  public void addOperator(Operator operator) {
    this.operatorList.add(operator);
  }
  
  public void setLevel(int level) {
    this.level = level;
  }

}
