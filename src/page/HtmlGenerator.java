package page;

import math.MathFomular;
import math.RandomGen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


public class HtmlGenerator extends RandomGen.Generator {
  protected StringBuffer htmlBuff = null;
  private static int ROW_PER_PAGE = 25;
  private static String SPACE = "&nbsp;";

  public boolean generate(File dest, int maxNumber, int exTotal) {
    Writer out = null;
    htmlBuff = new StringBuffer();
    MAX_ADD_LIMIT = MAX_SUB_LIMIT = maxNumber;
    NUM_OF_EXERCISE = exTotal;
    r2 = new RandomGen();

    genHader(maxNumber, exTotal);

    int rowCount = NUM_OF_EXERCISE/EXERCISE_PER_LINE;

    //htmlBuff.append("<H3 align='center'>"+ maxNumber+"以内加减法测试习题。共"+exTotal+"道题。</H3><HR>\r\n");
    htmlBuff.append("<body><table border='2' align='center' rules='all' cellspacing='2'>\r\n");
    for (int i=1; i<=rowCount; i++) {
    	//generate a line of fomular
      genHtmlTrOfExercise();
      addEmptyLline();
      if (i%ROW_PER_PAGE==0) {
        htmlBuff.append("</table>\r\n");
        htmlBuff.append("<div class='pagebreak'></div>");
        if ((i+1)<rowCount) {
          htmlBuff.append("<table border='2' align='center' rules='all' cellspacing='2'>\r\n");
        }
      }
    }

    genFooter();

    try {
      out = new FileWriter(dest);
      out.write(htmlBuff.toString());
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    } finally {
      htmlBuff = null;
      try {
        out.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return true;
  }

  protected void genHtmlTrOfExercise() {
    htmlBuff.append("<tr align='center' cellspacing='2'>\r\n");
    for (int i=0; i<EXERCISE_PER_LINE; i++) {
      htmlBuff.append("<td align='center' cellspacing='2'>"+generateExercise()+"</td>\r\n");
    }
    htmlBuff.append("</tr>");
  }
  
  protected String parseFomular (MathFomular f) {
    return getAllignStr(f.getIntFirstNum(), SPACE) + SPACE 
    		+ f.getOperator().getMathOperator()+ SPACE
    		+ getAllignStr(f.getIntSecondNum(), SPACE) + SPACE
    		+ "="+ SPACE+"("+SPACE+SPACE+SPACE+SPACE+SPACE+SPACE+ ")";
  }

  protected void genHader(int maxNumber, int exTotal) {
    htmlBuff.append("<html><head>\n");
    htmlBuff.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
    htmlBuff.append("<title>数学练习题</title>\r\n");
    htmlBuff.append("<style type=\"text/css\">\r\n");
    htmlBuff.append("table{");
    htmlBuff.append("      table-layout: fixed; ");
    htmlBuff.append("      border-collapse: collapse;");
    htmlBuff.append("      width: 70%;font-size:30px;");
    htmlBuff.append("}");
    htmlBuff.append(".pagebreak{page-break-after: always;}");
    htmlBuff.append("</style>");
    htmlBuff.append("</head>\r\n");
  }

  protected void genFooter() {
    htmlBuff.append("</table></body>");
    htmlBuff.append("</html>");
  }

  protected void addEmptyLline() {
    //do nothing
  }

}
