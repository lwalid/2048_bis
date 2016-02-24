package vue;

import Controleur.Controleur;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.BoardImpl;
import vue.Game2048;

public class MainApp extends Application {
	


	@Override
    public void start(Stage primaryStage) {
        
        BoardImpl board = new BoardImpl(4);      
        Game2048 game = new Game2048(board);       
        Controleur controler= new Controleur(board, game, primaryStage);
        GridPane root = controler.getIhmfx();
        
        Scene scene =new Scene(root, 400, 400);
		scene .getStylesheets().add(Game2048.class.getResource("game.css").toExternalForm());
	      root.getStyleClass().addAll("game-root");

    	
        primaryStage.setTitle("GAME 2048");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
