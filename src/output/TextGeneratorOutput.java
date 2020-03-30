package output;

import math.MathFomula;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Queue;

public class TextGeneratorOutput extends BaseGeneratorOutput{

  private StringBuffer mathBuff = null;
  private static final char LINE_SEPERATOR = '-';
  private static final char EXERCISE_SEPERATOR = '|';
  private static final int CELL_LENGTH = 18;
  private static final int MATH_PER_ROW = 4;
  private static final String SPACE = " ";
  

  @Override
  public boolean genOutput(File dest, int maxNumber, int mathCount){
    Writer out = null;
    mathBuff = new StringBuffer();

    try {
      out = new FileWriter(dest);
      out.write("  "+maxNumber+"以内加减法测试习题。共"+mathCount+"道题。\r\n");
      printALine(38);
      
      Queue<MathFomula> mathQueue = generate(maxNumber, mathCount);
      int rowCount = mathCount/MATH_PER_ROW;
      		
      for (int i=0; i<rowCount; i++) {
        printLineOfMath(mathQueue);
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
    return getAllignStr(f.getIntFirstNum()) + space() 
    		+ f.getOperator().getMathOperator() + space() 
    		+ getAllignStr(f.getIntSecondNum()) + space()
    		+ "="+ space()+"("+space()+space()+space()+ ")";
  }
  
  private void printLineOfMath(Queue<MathFomula> mathQueue) {
    for (int i=0; i<MATH_PER_ROW; i++) {
      printSpliter();
      printMathToText(parseFomular(mathQueue.poll()));
    }
    printSpliter();
    mathBuff.append("\r\n");
  }

  private void printMathToText(String mathString) {
    mathBuff.append(mathString);
  }

  private void printALine(int length) {
  	printSpace();
    for (int i = 0; i < CELL_LENGTH*MATH_PER_ROW; i++) {
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
    mathBuff.append(space());
  }

	@Override
	protected String space() {
		return SPACE;
	}

}
