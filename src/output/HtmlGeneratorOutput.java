package output;

import math.MathFomula;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;


public class HtmlGeneratorOutput extends BaseGeneratorOutput{
  protected StringBuffer htmlBuff = null;
  private static int ROW_PER_PAGE = 25;
  public static int EXERCISE_PER_LINE = 2;
  
  protected static String SPACE = "&nbsp;";

  @Override
  public boolean genOutput(File dest, int maxNumber, int mathCount) {
    Writer out = null;
    htmlBuff = new StringBuffer();

    genHader(maxNumber, mathCount);

    int rowCount = mathCount/EXERCISE_PER_LINE;
    htmlBuff.append("<body><table border='2' align='center' rules='all' cellspacing='2'>\r\n");
    
    Set<MathFomula> mathSet = generate(dest, maxNumber, mathCount);
    int i=1;
    for (MathFomula f: mathSet) {
    	//generate a line of fomular
      genHtmlTrOfExercise(f);
      addEmptyLline();
      if (i%ROW_PER_PAGE==0) {
        htmlBuff.append("</table>\r\n");
        htmlBuff.append("<div class='pagebreak'></div>");
        if ((i+1)<rowCount) {
          htmlBuff.append("<table border='2' align='center' rules='all' cellspacing='2'>\r\n");
        }
      }
      i++;
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

  protected void genHtmlTrOfExercise(MathFomula f) {
    htmlBuff.append("<tr align='center' cellspacing='2'>\r\n");
    for (int i=0; i<EXERCISE_PER_LINE; i++) {
      htmlBuff.append("<td align='center' cellspacing='2'>"+parseFomula(f)+"</td>\r\n");
    }
    htmlBuff.append("</tr>");
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

	@Override
	protected String space() {
		return SPACE;
	}

}
