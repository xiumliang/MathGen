package math;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class RandomGenerator {
	private int maxAddLimit = 0;
  private int maxSubLimit = 0;
  private int REMOVE_ZERO_RATIO = 10;
  private int REMOVE_ONE_RATIO = 5;
  
  private static Random r = null;
  private List<Operator> operatorList = new ArrayList<Operator>();
  private int level;
  
  Queue<MathFomula> mathQueue = null;

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
 
	public Queue<MathFomula> generate(int maxNumber, int mathCount) {
		mathQueue = new LinkedBlockingQueue();
		maxAddLimit = maxSubLimit = maxNumber;
		for (int i = 0; i <= mathCount; i++) {
			mathQueue.add(generateExercise());
		}
		return mathQueue;
	}

	public MathFomula generateAddition() {
		MathFomula f = new MathFomula();
		int a = r.nextInt(maxAddLimit + 1);
		int b = r.nextInt(maxAddLimit + 1 - a);

		for (int i = 0; checkRemoveRatio(i, a, b); i++) {
			a = r.nextInt(maxAddLimit + 1);
			b = r.nextInt(maxAddLimit + 1 - a);
		}

		// simply skip 1 + 1 =, because it is useless for an excercise.
		if ((a == 1) && (b == 1)) {
			for (int i = 0; checkRemoveRatio(i, a, b); i++) {
				a = r.nextInt(maxAddLimit + 1);
				b = r.nextInt(maxAddLimit + 1 - a);
			}
		}

		f.setFirstNum(String.valueOf(a));
		f.setOperator(OperatorEnum.ADD);
		f.setSecondNum(String.valueOf(b));
		return f;
	}

  public MathFomula generateSubtraction() {
  	MathFomula f = new MathFomula();
    int a = r.nextInt(maxSubLimit)+1;
    int b = r.nextInt(a);

    for(int i=0; checkRemoveRatio(i, a, b); i++) {
      a = r.nextInt(maxSubLimit)+1;
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
  
  public MathFomula generateDivision() {
  	MathFomula f = null;
  	switch (level) {
	  	case 1:
	  		f= generateNonExactDivision();
	  		break;
	  	case 2:
	  		if (r.nextBoolean())
	  			f = generateNonExactDivision();
	  		else
	  			f = generateExactDivision();
	  		break;
	  	case 3:
	  		f= generateExactDivision();
	  		break;
  	}
    return f;
  }

  public MathFomula generateExactDivision() {
  	MathFomula f = new MathFomula();
    int a = r.nextInt(9)+1;
    int b = r.nextInt(9)+1;

    for(int i=0; checkRemoveRatio(i, a, b); i++) {
      a = r.nextInt(9)+1;
      b = r.nextInt(9)+1;
    }

    f.setFirstNum(String.valueOf(a*b));
    f.setOperator(OperatorEnum.DIV);
    f.setSecondNum(String.valueOf(a));
    return f;
  }
  
  public MathFomula generateNonExactDivision() {
  	MathFomula f = generateExactDivision();
    int c = r.nextInt(f.getIntSecondNum()-1)+1;    
    f.setFirstNum(String.valueOf(f.getIntFirstNum()+c));
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
    		case DIV:
    			genedMath = generateDivision();
    			break;
    		default :
    				System.out.println("Invalid operator!");
    	}
    } while (mathQueue.contains(genedMath));
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
