package cop2251.fall17.week8.ellison;

import java.io.FileNotFoundException;

import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BabyNameChart extends Application
{
	XYChart.Series series = new XYChart.Series();

	// our helper class to fetch needed data
	NameHelper h = new NameHelper();
	
	/*
	 *  This method will draw the graph of name popularity vs year
	 */
	private void draw(String name, String gender)
	{
		series.getData().clear();		
		
		// first check if name is present
		if (h.isNamePresent(name, gender) == false)
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("ERROR");
			alert.setContentText(name + " not found!");
			alert.showAndWait(); 
			return;
		}
		
        for(String year : h.getYears())
		{
        	Integer rank =  h.getRank(year, name, gender);

        	// add a data point (year, rank) to our series.
			series.getData().add( new XYChart.Data(year,rank) );
			
			// for debug...
			//System.out.printf("%4s-%4d\n", year, h.getRank(year, name, gender));
		}  
        
	}
	
	public void start(Stage stage) throws FileNotFoundException 
	{
		h.load();
		
        stage.setTitle("Baby Name Popularity");
        
        final CategoryAxis xAxis = new CategoryAxis(); // for string values on x axis
        final NumberAxis yAxis   = new NumberAxis();   // for integer values on y axis
        
        // label our axis
        xAxis.setLabel("Year");
        yAxis.setLabel("Popularity");
        yAxis.setAutoRanging(true);
        
        //creating the chart.  Data will be added later.
 //       LineChart<String,Number> lineChart = new LineChart<String,Number>(xAxis,yAxis);    
        AreaChart<String,Number> chart = new AreaChart<String,Number>(xAxis,yAxis);             

        chart.setTitle("Popularity by Year");
        chart.getData().addAll(series);

        // setting animated off fixes an apparent bug with charts that don't have symbols
        // If animated = on and symbols = off, then it doesn't always redraw correctly at
        // the ends of the data range.
        chart.setAnimated(false);
        chart.setCreateSymbols(false);

        //-------------------------
    	// text field for name
        //-------------------------
        HBox paneForText = new HBox(5);
        Label lb1 = new Label("Name:");
        TextField tfMessage = new TextField();
        paneForText.setAlignment(Pos.CENTER);
        paneForText.getChildren().addAll(lb1,tfMessage);
                
        //-------------------------
	    // radio buttons for gender
        //-------------------------
        HBox panelForGender   = new HBox(10);
        RadioButton male   = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        male.setSelected(true);
        panelForGender.setAlignment(Pos.CENTER);
        panelForGender.getChildren().addAll(male,female);
        
        // add to group
        ToggleGroup group = new ToggleGroup();
        male.setToggleGroup(group);
        female.setToggleGroup(group);
        
        //-------------------------
        // button panel
        //-------------------------
        HBox paneForButtons = new HBox(5);      
        Button goButton = new Button();
        goButton.setText("Go!");
        tfMessage.setOnAction(new EventHandler<ActionEvent>()
        {            
            @Override
            public void handle(ActionEvent event)
            {
            	String gender = "M";
            	if (female.isSelected())
                    gender = "F";

            	String name = tfMessage.getText();
            	
            	// add label to series at bottom of chart
            	series.setName(name);  
            	
            	// get the data set for the name 
                draw(name,gender);
            }
        });
        paneForButtons.setAlignment(Pos.CENTER);
        paneForButtons.getChildren().addAll(goButton);
        
        //-------------------------------------------
        // H pane to hold all three previous panes
        //-------------------------------------------
        HBox topPane = new HBox(50);
        topPane.setPadding(new Insets(15, 5, 15, 15));
        topPane.setAlignment(Pos.CENTER);  
        topPane.getChildren().addAll(paneForText,panelForGender);//,paneForButtons);
        
        //------------------------------------------------------------
        //  vertical pane will hold the horizontal composite pane and the chart
        //-------------------------------------------------------------
        VBox vPane = new VBox(10);
        vPane.setPadding(new Insets(5, 5, 15, 15));
        vPane.getChildren().add(topPane);
        vPane.getChildren().add(chart);
        
        //------------------------------------------------
        // build and show scene
        //------------------------------------------------
        Scene scene = new Scene(vPane,800,500);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args)
    {
        launch(args);
    }
}
