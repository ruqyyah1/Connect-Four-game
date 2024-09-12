
import java.util.Observable;
import java.util.Scanner;


public class ConnectFourGame extends Observable {
    private int nColumns;
    private int nRows;
    private int numToWin;
    private ConnectFourEnum[][] grid;
    private ConnectFourEnum gameState;
    private ConnectFourEnum turn;
    
    /**
     * This constructor chains to the arguments constructor
     * @param initialTurn Parameter specifies which colour starts the game
     */
    public ConnectFourGame(ConnectFourEnum initialTurn){
        this(8,8,4,initialTurn);
    }

    /**
     * Checks if the correct number of rows and columns is inputted. Assigns each variable to its value
     * Then resets the board to make the grid.
     * @param nRows Rows in the game
     * @param nColumns Columns in the game
     * @param numToWin Number to win the game
     * @param initialTurn specifies which colour starts the game
     */
    public ConnectFourGame(int nRows, int nColumns, int numToWin, ConnectFourEnum initialTurn ){
        if (nRows < 0 || nColumns < 0 )
            throw new IllegalArgumentException ("Number of Rows and Columns should be more than 0");
        if (numToWin > nRows || numToWin > nColumns) {
            throw new IllegalArgumentException("The number to Win must be equal or less than the number of Columns and Rows");
        }
        this.nRows = nRows;
        this.nColumns = nColumns;
        this.numToWin = numToWin;        
        reset(initialTurn);
       
    }

    /**
     * Creates the grid filled with EMPTY
     * @param initialTurn initializes the turn variable to the initialTurn specified to start
     */
    public void reset(ConnectFourEnum initialTurn){
        this.turn = initialTurn;
        this.grid = new ConnectFourEnum[this.nRows][this.nColumns];        
        
        for(int i=0;i<nRows;i++){
            for(int j=0;j<nColumns;j++){
                this.grid[i][j] = ConnectFourEnum.EMPTY;
            }
        }
        
        gameState = ConnectFourEnum.IN_PROGRESS;
    }

    /**Checks if the player inputted the correct row and column value(if its already filled or if there are any
     * empty boxes under it. Also, takeTurn checks the winner of the game each time a turn is played. Also, calls the setChanged method by the Obzervable
     *  as the buttons in the GUI will be changed as long as it passes all the exceptions
     * @param row the row specified to input the turn by the player
     * @param column the column specified to input the turn by the player
     * @return returning the gamestate
     */
    public ConnectFourEnum takeTurn(int row, int column){
        if (this.gameState != ConnectFourEnum.IN_PROGRESS){ 
            throw new IllegalArgumentException("Game Finished, No more turns available");
        }
        if(row != 0){
            if(this.grid[row-1][column] == ConnectFourEnum.EMPTY ){
            throw new IllegalArgumentException("Enter valid Number of Row and Column");
            }
        }
        if(this.nRows>row && row>=0 && this.nColumns>column && column>=0 && this.grid[row][column]== ConnectFourEnum.EMPTY){
            grid[row][column] = this.turn;
        }else{throw new IllegalArgumentException("Enter valid Number of Row and Column");}
        
        int roww = 0;
        for(int i = row ; i>=0; i--){
            if(this.turn == grid[i][column]){
                roww++;
            }else{
                roww = 0;
            }
            if(roww == numToWin){
                this.gameState = this.turn;
            }
        }
       
        
        
        int columnn = 0;
        for(int i = 0 ; i<this.nColumns; i++){
            if(this.turn == grid[row][i]){
                columnn++;
            }else{
                columnn = 0;
            }
            if(columnn == numToWin){
                this.gameState = this.turn;
            }
        }       
        
        int draw = 0;
        for(int i=0;i<this.nRows;i++){
            for(int j=0;j<this.nColumns;j++){
                if(grid[i][j] != ConnectFourEnum.EMPTY){
                    draw++;
                }
            }
        }
        if(draw == (this.nColumns * this.nRows)){
            this.gameState = ConnectFourEnum.DRAW;
        }
        
        
        if(this.turn == ConnectFourEnum.BLACK){
            this.turn = ConnectFourEnum.RED;
        }else
        if(this.turn == ConnectFourEnum.RED){
            this.turn = ConnectFourEnum.BLACK;
        }
        this.setChanged();
       
        return getGameState();
    }

    /**
     *
     * @return returns the gamestate if its inprogress or the red or black won or draw.
     */
    public ConnectFourEnum getGameState(){        
        return this.gameState;
    }

    /**
     *
     * @return returns which turn it is in the game
     */
    public ConnectFourEnum getTurn(){
        return this.turn;
    }
    /*
    * Returns a String representation of the current board of the game
    */
    @Override
    public String toString(){
        String[][] s = new String[this.nRows][this.nColumns];
        String s1 = new String();
        
        for(int i=0;i<this.nRows;i++){
            for(int j=0;j<this.nColumns;j++){
                s[i][j] = grid[i][j] + " | ";
                s1 = s1.concat(s[i][j]);
            }
           s1= s1.concat("\n");
        }       
        return s1; 
    }
    
}
