package output;

import math.MathFomula;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TextGeneratorOutput extends BaseGeneratorOutput{

  private StringBuffer mathBuff = null;
  private static final char LINE_SEPERATOR = '-';
  private static final char EXERCISE_SEPERATOR = '|';
  private static String SPACE = " ";
  public static int EXERCISE_PER_LINE = 2;

  @Override
  public boolean genOutput(File dest, int maxNumber, int mathCount){
    Writer out = null;
    mathBuff = new StringBuffer();

    try {
      out = new FileWriter(dest);
      out.write("  "+maxNumber+"以内加减法测试习题。共"+mathCount+"道题。\r\n");
      printALine(38);
      for (int i=0; i<(mathCount/EXERCISE_PER_LINE); i++) {
        printLineOfMath();
        printALine(38);
      }
      out.write(mathBuff.toString());
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    } finally {
      mathBuff = null;
      try {
        out.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return true;
  }

  protected String parseFomular (MathFomula f) {
    return getAllignStr(f.getIntFirstNum()) + SPACE 
    		+ f.getOperator().getMathOperator()
    		+ getAllignStr(f.getIntSecondNum()) + SPACE
    		+ " = "+ SPACE+"("+SPACE+SPACE+SPACE+SPACE+ ")";
  }
  
  private void printLineOfMath() {
    for (int i=0; i<EXERCISE_PER_LINE; i++) {
      printSpliter();
      //printExerciseToText(generateExercise());
    }
    printSpliter();
    mathBuff.append("\r\n");
  }

  private void printMathToText(String math) {
    mathBuff.append(math);
  }

  private void printALine(int length) {
    mathBuff.append("  ");
    for (int i = 0; i < length; i++) {
      mathBuff.append(LINE_SEPERATOR);
    }
    mathBuff.append(LINE_SEPERATOR+"\r\n");
  }

  private void printSpliter() {
    printSpace();
    mathBuff.append(EXERCISE_SEPERATOR);
    printSpace();
  }

  private void printSpace() {
    mathBuff.append(" ");
  }

	@Override
	protected String space() {
		return SPACE;
	}


}
