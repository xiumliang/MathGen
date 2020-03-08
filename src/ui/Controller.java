package ui;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;
import math.OperatorEnum;
import math.RandomGen;
import output.HtmlGenerator;
import output.TextGenerator;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Controller {
	private File outputFile = null;

  protected void handleSubmitButtonAction(ActionEvent event) {
    if (!checkValues())
      return;

    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("HTML Files", "*.html"));
    fileChooser.getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
    fileChooser.setTitle("请选择输出文件");
    fileChooser.setInitialFileName("Exercise.html");
    fileChooser.setInitialDirectory(
        new File(System.getProperty("user.home"))
    );
    File file = fileChooser.showSaveDialog(Main.getMainStage());
    if (file != null) {
      System.out.println(file);
    }

    RandomGen.Generator generator = null;
    String extName = getExtName(file);
		if (extName == null) {
			getMainStage().getResultText().setText("");
			return;
		} else if ("html".equalsIgnoreCase(extName))
			generator = new HtmlGenerator();
		else
			generator = new TextGenerator();
		
		List<CheckBox> selectedTypeList = getMainStage().getMathTypeList().stream()
		    .filter((mathType) -> mathType.isSelected()).collect(Collectors.toList());		
		for (CheckBox cb: selectedTypeList) 
			generator.addOperator(OperatorEnum.retrieveOperator(cb.textProperty().getValue()));

    generator.setLevel(Integer.parseInt(getMainStage().getLevel().getValue().toString()) );
    generator.generate(file, 
    		Integer.parseInt(getMainStage().getMaxNumber().getText()), 
    		Integer.parseInt(getMainStage().getTotalMath().getText()));
    
    getMainStage().getResultText().setText(getMainStage().getTotalMath().getText()+"道习题已生成.");
    getMainStage().getOutFileText().setText("文件:"+file);
    outputFile = file;
    if (Desktop.isDesktopSupported())
	    try {
				Desktop.getDesktop().open(file);
			} catch (IOException e) {
				System.out.println("打开生成文件出错。");
				e.printStackTrace();
			}
  }

  private boolean checkValues() {
    if (!isNumeric(getMainStage().getMaxNumber().getText())
        || (Integer.parseInt(getMainStage().getMaxNumber().getText())>100)
        || (Integer.parseInt(getMainStage().getMaxNumber().getText())<5)){
    	getMainStage().getResultText().setText("最大数值填写有误,请填写5-100之间的数字");
      return false;
    }

    if (!isNumeric(getMainStage().getTotalMath().getText())
        || (Integer.parseInt(getMainStage().getTotalMath().getText())>1000)
        || (Integer.parseInt(getMainStage().getTotalMath().getText())<20)){
    	getMainStage().getResultText().setText("题目数量填写有误,请填写20-1000之间的数字");
      return false;
    }

    return true;
  }

  private boolean isNumeric(String str){
    if (isNull(str))
      return false;

    Pattern pattern = Pattern.compile("[0-9]*");
    return pattern.matcher(str).matches();
  }

  private boolean isNull(String str) {
    return (str ==null || str.length()==0);
  }


  private String getExtName(File file) {
    if (file == null)
      return null;

    String filename = file.toString();
    if (isNull(filename) || filename.indexOf('.')<0)
      return null;

    if ((filename != null) && (filename.length() > 0)) {
      int dot = filename.lastIndexOf('.');
      if ((dot >-1) && (dot < (filename.length() - 1))) {
        return filename.substring(dot + 1);
      }
    }
    return filename;
  }
  
  private MainStage getMainStage() {
  	return Main.getMainStage();
  }

	protected File getOutputFile() {
		return outputFile;
	}
	
	
}
