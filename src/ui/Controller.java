package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import math.OperatorFactory;
import math.RandomGen;
import page.TextGenerator;
import page.HtmlGenerator;

import java.io.File;
import java.util.regex.Pattern;

public class Controller {

  @FXML
  private Text resultText;
  @FXML
  private TextField maxNumber;
  @FXML
  private TextField exTotal;
  @FXML
  private CheckBox math_addition;
  @FXML
  private CheckBox math_subtraction;
  @FXML
  private CheckBox math_multiplication;
  @FXML
  private ComboBox level;

  @FXML
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
    File file = fileChooser.showSaveDialog(Main.getStage());
    if (file != null) {
      System.out.println(file);
    }

    RandomGen.Generator generator = null;

    String extName = getExtName(file);

    if (extName == null) {
      resultText.setText("");
      return;
    } else if ("html".equalsIgnoreCase(extName)) {
        generator = new HtmlGenerator();
    } else
      generator = new TextGenerator();

    if (math_addition.isSelected()) {
      generator.addOperator(OperatorFactory.getOperatorInstance('+'));
    }
    if (math_subtraction.isSelected()) {
      generator.addOperator(OperatorFactory.getOperatorInstance('-'));
    }
    if (math_multiplication.isSelected()) {
      generator.addOperator(OperatorFactory.getOperatorInstance('/'));
    }
//    if (math_subtraction.isSelected()) {
//      generator.addOperator(OperatorFactory.getOperatorInstance('-'));
//    }
    
    generator.setLevel(Integer.parseInt(level.getValue().toString()) );

    /*
    System.out.println("mathAdd="+ math_addition.isSelected());
    System.out.println("mathSub="+ math_subtraction.isSelected());
    
    */

    generator.generate(file, Integer.parseInt(maxNumber.getText()), Integer.parseInt(exTotal.getText()));
    resultText.setText(exTotal.getText()+"道习题已生成,最大值是"+maxNumber.getText()+".\r\n 生成文件位于:"+file);
  }

  private boolean checkValues() {
    if (!isNumeric(maxNumber.getText())
        || (Integer.parseInt(maxNumber.getText())>100)
        || (Integer.parseInt(maxNumber.getText())<5)){
      resultText.setText("最大数值填写有误,请填写5-100之间的数字");
      return false;
    }

    if (!isNumeric(exTotal.getText())
        || (Integer.parseInt(exTotal.getText())>1000)
        || (Integer.parseInt(exTotal.getText())<20)){
      resultText.setText("题目数量填写有误,请填写20-1000之间的数字");
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
}
