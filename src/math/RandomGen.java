package math;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class RandomGen {

  private static Random r = null;

  public RandomGen() {
    r = new Random(getSeed());
  }

  protected int nextInt(int bound) {
    return r.nextInt(bound);
  }

  protected boolean nextBoolean(){ return r.nextBoolean();}

  private int getSeed(){
    long lSeed = System.currentTimeMillis();
    return (int) (lSeed%10000);
  }

  public abstract static class Generator {
    public RandomGen r = new RandomGen();
    public int MAX_ADD_LIMIT = 0;
    public int MAX_SUB_LIMIT = 0;
    public int EXERCISE_PER_LINE = 2;
    public int NUM_OF_EXERCISE = 0;
    public int REMOVE_ZERO_RATIO = 10;
    public int REMOVE_ONE_RATIO = 5;

    private List<Operator> operatorList = new ArrayList<Operator>();
    private int level;

    //the Q is used for checking duplicated formular.
    public Queue<String> mathQ = new FixedSizeQueue<String>(50);

    public RandomGen r2 = null;
    public abstract boolean generate(File dest, int maxNumber, int exTotal);

    public MathFomular generateAddition() {
    	MathFomular f = new MathFomular();
      int a = r.nextInt(MAX_ADD_LIMIT + 1);
      int b = r.nextInt(MAX_ADD_LIMIT + 1 - a);

      for(int i=0; checkRemoveRatio(i, a, b); i++) {
        a = r.nextInt(MAX_ADD_LIMIT + 1);
        b = r.nextInt(MAX_ADD_LIMIT + 1 - a);
      }

      //simply skip 1 + 1 =, because it is useless for an excercise.
      if ((a==1) && (b==1)) {
        for(int i=0; checkRemoveRatio(i, a, b); i++) {
          a = r.nextInt(MAX_ADD_LIMIT + 1);
          b = r.nextInt(MAX_ADD_LIMIT + 1 - a);
        }
      }

      f.setFirstNum(String.valueOf(a));
      f.setOperator(OperatorEnum.ADD);
      f.setSecondNum(String.valueOf(b));
      return f;
    }

    public MathFomular generateSubtraction() {
    	MathFomular f = new MathFomular();
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
    
    public MathFomular generateMultiplication() {
    	MathFomular f = new MathFomular();
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

    /*
     * the impl of parseFomular may be diverse
     * according to the format it finally printout.
     */
    protected abstract String parseFomular (MathFomular f);
    
    public String generateExercise() {
      String exercise = "";
      do {
      	switch ((OperatorEnum)randomOperator()) {
      		case ADD:
      			exercise = parseFomular(generateAddition());
      			break;
      		case SUB:
      			exercise = parseFomular(generateSubtraction());
      			break;
      		case MUL:
      			exercise = parseFomular(generateMultiplication());
      			break;
      		default :
      				System.out.println("Invalid operator!");
      	}
      } while (mathQ.contains(exercise));
      mathQ.add(exercise);
      return exercise;
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

    protected String getAllignStr(int num, String space) {
      return (num>9)? (""+num): (space + num);
    }
  }
}
