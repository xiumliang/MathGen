package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainStage extends Stage {
	private  static Integer[] LEVELS = {1, 2, 3};
	
	private Text welcomeText = new Text();
	private Text resultText = new Text();
	private Text maxNumberValidText = new Text();
	private Text totalMathValidText = new Text();
	private Text outFileText = new Text();
	private TextField maxNumber     = new TextField();
  private TextField totalMath   = new TextField();
  
  List<CheckBox> mathTypeList = new ArrayList();
  CheckBox mathAddition = new CheckBox("+");
  CheckBox mathSubtraction = new CheckBox("-");
  CheckBox mathMultiplication = new CheckBox("×");
  CheckBox mathDivision = new CheckBox("÷");
  
  ComboBox<Integer> level = new ComboBox<Integer>();
  
  private static Label maxNumberLabel  = new Label("最大数值(5-100):");
  private static Label totalMathLabel  = new Label("题目数量(20-1000):");
  private static Label mathTypeLabel   = new Label("运算类型:");
  private static Label levelLabel      = new Label("难度(1-最难,3-简单):");
  
  
  public MainStage() {
		init();
	}

	private void init() {
  	maxNumberLabel.setWrapText(false);
  	maxNumberLabel.setMinWidth(80);
  	totalMathLabel.setWrapText(false);
  	mathTypeLabel.setWrapText(false);
    
  	maxNumber.setMaxWidth(50);
  	totalMath.setMaxWidth(50);
  	level.setMaxWidth(100);
  	welcomeText.setWrappingWidth(150);
  	resultText.setWrappingWidth(150);
  	maxNumberValidText.setWrappingWidth(150);
  	totalMathValidText.setWrappingWidth(150);
  	outFileText.setWrappingWidth(200);
  	mathAddition.setMaxWidth(100);
  	mathSubtraction.setMaxWidth(100);
  	mathMultiplication.setMaxWidth(100);
  	mathDivision.setMaxWidth(100);
  	
  	resultText.setFill(Color.RED);
  	maxNumberValidText.setFill(Color.RED);
  	totalMathValidText.setFill(Color.RED);
  	outFileText.setFill(Color.RED);
  	
  	level.setVisibleRowCount(3);
    level.getItems().addAll(Arrays.asList(LEVELS));
    level.setValue(2);
    
    mathTypeList.add(mathAddition);
    mathTypeList.add(mathSubtraction);
    mathTypeList.add(mathMultiplication);
    mathTypeList.add(mathDivision);
  }
  
  
  public boolean showStage () {    
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setStyle("-fx-padding: 10;" + "-fx-border-width: 10;" 
        + "-fx-border-insets: 10;" + "-fx-border-radius: 5;");
    
    Scene mathDefineScene = new Scene(grid, 500, 360);
    mathDefineScene.setFill(Color.LIGHTGRAY);
    this.setScene(mathDefineScene);
    this.setTitle("ExerciseGen");
    
    int line = 0;
    
    //------------- welcome text ---------------
    welcomeText.setText("请根据需求选择相关参数");   
    grid.add(welcomeText, 1, line);
    line++;
    
    //------------ set max number ---------------
    maxNumber.setText("100");
    grid.add(maxNumberLabel, 0, line);
    grid.add(maxNumber, 1, line);
    grid.add(maxNumberValidText, 2, line);
    line++;
    
    //------------ Set total math ---------------
    totalMath.setText("50");
    grid.add(totalMathLabel, 0, line);
    grid.add(totalMath, 1, line);
    grid.add(totalMathValidText, 2, line);
    line++;
        
    //------------ Select Math types ---------------
    grid.add(mathTypeLabel, 0, line);
    line++;
    
    mathAddition.setSelected(true);
    mathSubtraction.setSelected(true);
    grid.add(mathAddition, 1, line);
    grid.add(mathSubtraction, 2, line);
    line++;
    
    grid.add(mathMultiplication, 1, line);
    grid.add(mathDivision, 2, line);
    mathDivision.setDisable(true);
    line++;
    
    
   //------------ Set level ---------------
    level.setPromptText("难度(1-最难,3-简单)");
    grid.add(levelLabel, 0, line);
    grid.add(level, 1, line);
    line++;
    
    //------------ Back Button ----------------
    Button genBtn = new Button("生成");
    genBtn.setPrefWidth(60);
    genBtn.setOnAction((actionEvent)->{
    	cleanTextFields();
      Main.getController().handleSubmitButtonAction(actionEvent);
    });    
    grid.add(genBtn, 1, line);
    line++;
    
    //------------ result text (if any) -----------------
    grid.add(resultText, 1, line);
    
		outFileText.setOnMouseClicked((actionEvent) -> openGeneratedTestFolder());
		grid.add(outFileText, 2, line);
    
    this.show();
    System.out.println("show Main Stage!!!");
    return true;
  }
  
	private void openGeneratedTestFolder() {
		if (Main.getController().getOutputFile() != null)
			try {
				ProcessBuilder pb = new ProcessBuilder("open", Main.getController().getOutputFile().toString());
				pb.start();
			} catch (IOException e) {
				System.out.println(e);
			}
	}

	private void cleanTextFields() {
		maxNumberValidText.setText("");
		totalMathValidText.setText("");
		resultText.setText("");
		outFileText.setText("");
	}
	
	protected Text getResultText() {
		return resultText;
	}

	protected TextField getMaxNumber() {
		return maxNumber;
	}

	protected TextField getTotalMath() {
		return totalMath;
	}

	protected Text getMaxNumberValidText() {
		return maxNumberValidText;
	}

	protected Text getTotalMathValidText() {
		return totalMathValidText;
	}

	protected ComboBox<Integer> getLevel() {
		return level;
	}

	protected List<CheckBox> getMathTypeList() {
		return mathTypeList;
	}

	protected Text getOutFileText() {
		return outFileText;
	}
  
}
