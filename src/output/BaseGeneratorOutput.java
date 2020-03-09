package output;

import java.io.File;

import math.MathFomula;
import math.RandomGenerator;

public abstract class BaseGeneratorOutput extends RandomGenerator{
		
	public abstract boolean genOutput(File dest, int maxNumber, int mathCount);
	
	protected abstract String space();
	
	protected String getAllignStr(int num) {
    return (num>9)? (""+num): (space() + num);
  }
	
  protected String parseFomula (MathFomula f) {
  	if (f==null)
  		System.out.println("null value of a math fomula.");
    return getAllignStr(f.getIntFirstNum()) + space() 
    		+ f.getOperator().getMathOperator()+ space()
    		+ getAllignStr(f.getIntSecondNum()) + space()
    		+ "="+ space()+"("+space()+space()+space()+space()+space()+space()+ ")";
  }
 
}
