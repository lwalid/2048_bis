package vue;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.BoardImpl;


public class Game2048 {
    

    BoardImpl board;
    GridPane root=new GridPane(); 
    private List<String> colors = new ArrayList<String>();
    private Label [][] tabLabel=new Label[4][4];
    
    public Game2048(BoardImpl board) {
		this.board=board;
		root.setHgap(2);
		root.setVgap(2);
	}

    private Label createCell(int i, int j){
  	
    	   Label label = new Label();
    	   label.setPrefSize(100,100);
    	   label.setMinSize(100,100);
    	   
    	   label.getStyleClass().add("classLabel");
    	   if (board.getTile(j+1,i+1) != null) {
    		   label.setText(board.getTile(j+1,i+1).getRank()+"");
    		   label.getStyleClass().add("color"+board.getTile(j+1,i+1).getRank());
    	   } else {
    		   label.getStyleClass().add("color0");
    	   }    	   

    		return label;
        }
    
    
    public GridPane createGrid() {  
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
            	tabLabel[i][j]=createCell(i, j);
                root.add(tabLabel[i][j], i, j);
            }
        }
        return root;
    
    }
    
    
    public GridPane setGrid() {  
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
            	tabLabel[i][j].setText("0");;
                root.add(tabLabel[i][j], i, j, 100,100);
            }
        }
        return root;
        

    }

    
}
