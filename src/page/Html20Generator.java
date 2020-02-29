package page;

import java.io.File;

public class Html20Generator extends HtmlGenerator{

  public boolean generate(File dest, int maxNumber, int exTotal) {
    //super.ROW_PER_PAGE = 12;
    return super.generate(dest, maxNumber, exTotal);
  }


  protected void addEmptyLline() {
    htmlBuff.append("<tr align='center' cellspacing='2'><td>&nbsp;</td><td>&nbsp;</td></tr>");
  }
}
