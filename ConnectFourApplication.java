

import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ConnectFourApplication extends Application implements Observer {
   
    public int NUM_COLUMNS = 8;
    public int NUM_ROWS = 8;
    public int NUM_TO_WIN = 4;
    public int BUTTON_SIZE = 20;
    private ConnectFourGame gameEngine;
    private ConnectButton[][] buttons;
    private ConnectMove cm;
    
    /*
    *The start method creates the GUI of the game( The game, the board , the buttons and the textfield)
    */
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();
        gameEngine = new ConnectFourGame(NUM_ROWS, NUM_COLUMNS, NUM_TO_WIN,ConnectFourEnum.BLACK);
        gameEngine.addObserver(this);
        HBox hb = new HBox();
        TextField tf = new TextField("It's Black's turn");

        hb.getChildren().add(tf);
        HBox hb1 = new HBox();
        
        
        buttons = new ConnectButton[NUM_ROWS][NUM_COLUMNS];
        
        for(int i=0; i<NUM_ROWS; i++){
            for(int j=0; j<NUM_COLUMNS; j++){
                buttons[i][j] = new ConnectButton("EMPTY",i,j);
                buttons[i][j].setMinHeight(20);
                buttons[i][j].setMaxWidth(Double.MAX_VALUE);
                buttons[i][j].setOnAction(new ButtonHandler(tf));
                gp.add(buttons[i][j],j,NUM_ROWS-i-1);
            }
        }
        
        Button button = new Button("Take My Turn");
       
        
        button.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
               ConnectFourEnum cfe = gameEngine.takeTurn(cm.getRow(), cm.getColumn());
            
            if(tf.getText().equals("It's Black's turn")){
                tf.setText("It's Red's turn");
            }else{
                tf.setText("It's Black's turn");
            }
            
            gameEngine.notifyObservers(cm);
           
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Alert");
            alert.setHeaderText("Game Over");
            
            if(cfe.equals(ConnectFourEnum.BLACK) ){             
                alert.setContentText("BLACK WINS");
                alert.showAndWait();
                gameEngine.reset(ConnectFourEnum.BLACK);
                
                for(int i=0; i<NUM_ROWS; i++){
                    for(int j=0; j<NUM_COLUMNS; j++){
                        buttons[i][j].setText("EMPTY");
                        buttons[i][j].setDisable(false);
                    }
                }
            }else if(cfe.equals(ConnectFourEnum.DRAW)){
                alert.setContentText("DRAW");
                alert.showAndWait();
                gameEngine.reset(ConnectFourEnum.BLACK);
                
                for(int i=0; i<NUM_ROWS; i++){
                    for(int j=0; j<NUM_COLUMNS; j++){
                        buttons[i][j].setText("EMPTY");
                        buttons[i][j].setDisable(false);
                    }
                }
            }else if(cfe.equals(ConnectFourEnum.RED)){
                alert.setContentText("RED WINS");
                alert.showAndWait();
                gameEngine.reset(ConnectFourEnum.BLACK);
                
                for(int i=0; i<NUM_ROWS; i++){
                    for(int j=0; j<NUM_COLUMNS; j++){
                        buttons[i][j].setText("EMPTY");
                        buttons[i][j].setDisable(false);
                    }
                }
            }
            }
            
        });
        hb1.getChildren().add(button);
        bp.setCenter(gp);
        bp.setTop(hb);
        bp.setBottom(hb1);
        Scene scene = new Scene(bp,510,380);
        primaryStage.setTitle("ConnectFour");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /*
    * Launches the game GUI
    */
    public static void main(String args[])  { 
        launch(args);
    }
    
    /*
    * Called by calling the notifyObservers method by the Take my turn button. Disabling the button and putting the color of the player on the button
    */
    @Override
    public void update(Observable gameEngine, Object arg) {      
       String s = "" + cm.getColour();
       buttons[cm.getRow()][cm.getColumn()].setText(s);
       buttons[cm.getRow()][cm.getColumn()].setDisable(true);      
    }    
   
    /*
    * An inner class to initialize the Connect Move to be used in the update method and by Take my turn method.
    */
    private class ButtonHandler implements EventHandler<ActionEvent> {
        private TextField player;

        public ButtonHandler(TextField tf) {
            this.player = tf;
        }
        
        @Override
        public void handle(ActionEvent event) {
           Object source  = event.getSource();
           ConnectButton clickedButton = (ConnectButton)source;

           if(player.getText().equals("It's Black's turn")){
               cm = new ConnectMove(clickedButton.getRow(),clickedButton.getColumn() ,ConnectFourEnum.BLACK );               
           }else{
               cm = new ConnectMove(clickedButton.getRow(), clickedButton.getColumn() ,ConnectFourEnum.RED );                
           }
           
        }
    }

}
