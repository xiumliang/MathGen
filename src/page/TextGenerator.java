package page;

import math.MathFomular;
import math.RandomGen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TextGenerator extends RandomGen.Generator {

  private StringBuffer exBuff = null;
  private static final char LINE_SEPERATOR = '-';
  private static final char EXERCISE_SEPERATOR = '|';
  private static char SPACE = 32;

  public boolean generate(File dest, int maxNumber, int exTotal){
    Writer out = null;
    exBuff = new StringBuffer();
    MAX_ADD_LIMIT = MAX_SUB_LIMIT = maxNumber;
    NUM_OF_EXERCISE = exTotal;
    r2 = new RandomGen();
    try {
      out = new FileWriter(dest);
      out.write("  "+maxNumber+"以内加减法测试习题。共"+exTotal+"道题。\r\n");
      printALine(38);
      for (int i=0; i<(NUM_OF_EXERCISE/EXERCISE_PER_LINE); i++) {
        printLineOfExercise();
        printALine(38);
      }
      out.write(exBuff.toString());
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    } finally {
      exBuff = null;
      try {
        out.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return true;
  }

  protected String parseFomular (MathFomular f) {
    return getAllignStr(f.getIntFirstNum(), ""+SPACE) + SPACE 
    		+ f.getOperator().getMathOperator()
    		+ getAllignStr(f.getIntSecondNum(), ""+SPACE) + SPACE
    		+ " = "+ SPACE+"("+SPACE+SPACE+SPACE+SPACE+ ")";
  }
  
  private void printLineOfExercise() {
    for (int i=0; i<EXERCISE_PER_LINE; i++) {
      printSpliter();
      printExerciseToText(generateExercise());
    }
    printSpliter();
    exBuff.append("\r\n");
  }

  private void printExerciseToText(String exercise) {
    exBuff.append(exercise);
  }

  private void printALine(int length) {
    exBuff.append("  ");
    for (int i = 0; i < length; i++) {
      exBuff.append(LINE_SEPERATOR);
    }
    exBuff.append(LINE_SEPERATOR+"\r\n");
  }

  private void printSpliter() {
    printSpace();
    exBuff.append(EXERCISE_SEPERATOR);
    printSpace();
  }

  private void printSpace() {
    exBuff.append(" ");
  }


}
