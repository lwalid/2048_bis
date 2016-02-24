package Controleur;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.Board.Direction;
import model.BoardImpl;
import model.Tile;
import vue.Game2048;



public class Controleur {

	GridPane ihmfx; 
	BoardImpl board;
	private List<String> colors = new ArrayList<String>();
	Game2048 gameView;
	private Stage stage;
	
	public Controleur(BoardImpl board, Game2048 gameView, Stage primaryStage) {
		super();
		this.board = board;
		this.gameView = gameView;		
		
		board.addTileRandomly();
		this.ihmfx = gameView.createGrid();
		setStage(primaryStage);
		
		
		stage.addEventHandler(KeyEvent.KEY_RELEASED, (e) ->  {
			handleMove(e);
		}); 
		
		

	}

	public void getGameView() {
		this.ihmfx = gameView.createGrid();
	}
	
	public GridPane getIhmfx() {
		return ihmfx;
	}

	public void setIhmfx(GridPane ihmfx) {
		this.ihmfx = ihmfx;
	}

	public void setGameView(Game2048 gameView) {
		this.gameView = gameView;
	}


	public void handleMove(KeyEvent e) {
		boolean move;
		
		System.out.println(e.getCode().getName());
		switch (e.getCode().getName()) {		
		case "Left":
			board.packIntoDirection(Direction.LEFT);
			move = true;
			break;

		case "Right":
			board.packIntoDirection(Direction.RIGHT);
			move = true;
			break;

		case "Up":
			board.packIntoDirection(Direction.TOP);
			move = true;
			break;

		case "Down":
			board.packIntoDirection(Direction.BOTTOM);
			move = true;				
			break;

		default:
			move = false;
			break;
		}
		
		board.commit();
		gameView.createGrid();
		

		if (move && board.wasModified()) {
			board.addTileRandomly();
			gameView.createGrid();
			if (board.isGameOver()) {
				Text gameover = new Text("Game over!");
				gameover.setFont(Font.font("Arial", FontWeight.BOLD, 22));
				gameover.setFill(Color.BLUE);

				Popup popup = new Popup();
				popup.getContent().add(gameover);
				popup.show(ihmfx.getScene().getWindow());
				

			} else {
				board.addTileRandomly();
			}
		}


	}

	public void setStage(Stage primaryStage) {
		this.stage=primaryStage;
		
	}

//
	private void updateGrid() {
		for (Node child: ihmfx.getChildren()) {
			// Recuperation du tile de la case courante
			Tile t = board.getTile(GridPane.getRowIndex(child)+1, GridPane.getColumnIndex(child)+1);

			// Suppression du contenue de la case
			StackPane p = (StackPane) child;
			p.getChildren().clear();

			// Maj de la case
			if (t != null) {
				int value = (int) Math.pow(2, t.getRank());

				Label l = new Label(value+"");
				l.setPrefWidth(200);
				l.setPrefHeight(200);
				l.setAlignment(Pos.CENTER);
				l.setFont(Font.font("Arial", FontWeight.BOLD, 26));
				l.setTextFill(Color.GREY);
				try {
				l.setStyle("-fx-background-color: "+colors.get(t.getRank()));
				} catch (IndexOutOfBoundsException ignore) {
					l.setStyle("-fx-background-color: #ff0000");
				}

				p.getChildren().add(l);
				p.setAlignment(Pos.CENTER);
		}
		}
	}
}

