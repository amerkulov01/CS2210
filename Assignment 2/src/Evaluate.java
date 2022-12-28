/**
 * @author Andrei Merkulov
 * 	251145994
 *         Implements all the auxiliary methods needed by the algorithm 
 *         that plays the game
 */

public class Evaluate {	
    private char[][] gameBoard;	//initialising gameBoard
    private int size = 0;	//size of board
    private int tilesToWin = 0;
    
    public Evaluate (int size, int titlesToWin, int maxlevels){
        this.size =size;
        gameBoard =new char [size][size];	//creating a new board of the new size
        this.tilesToWin=titlesToWin;
        for (int i =0; i<size;i++){
            for (int j =0; j <size;j++) {
                gameBoard[i][j] = 'e';
            }
        }
    }

    public Dictionary createDictionary(){	// returns an empty Dictionary of the size i selected
        return new Dictionary(143);	// prime number

    }

    public Record repeatedState(Dictionary dict){	//contains the contents of the gameBoard array
        StringBuilder string = new StringBuilder();
        for (int i = 0;i < size; i++){
            for (int j = 0;j < size; j++){	//checking whether there is a record in dict with string as key attribute
                string.append(gameBoard[i][j]);
            }
        }
        return (dict.get(string.toString()));
    }

    public void insertState(Dictionary dict, int score, int level){	//represents content of gameBoard as a string
        StringBuilder string = new StringBuilder();
        for (int i = 0; i <size; i++){	
            for (int j = 0;j < size;j++){
                string.append(gameBoard[i][j]);
            }
        }
        Record insert = new Record(string.toString(), score, level);	//creating an object of class Record storing this string, score and level
        dict.put(insert);	//stored in dict
    }

    public void storePlay(int row,int col,char symbol){	//stores symbol in gameBoard[row][col]
        gameBoard[row][col] = symbol;
    }

    public boolean squareIsEmpty(int row, int col) {	//returns true if gameBoard = e
        if (gameBoard[row][col] == 'e')
            return true;
        else
            return false;
    }
    
    public boolean tileOfComputer(int row, int col) {		// returns true if gameBoard = c
        if (gameBoard[row][col] == 'c')
            return true;
        else
            return false;
    }
    
    public boolean titleOfHuman(int row, int col) {		//returns true if gameBoard = h
        if (gameBoard[row][col] == 'h')
            return true;
        else
            return false;
    }

    public boolean wins (char symbol){	//returns true if there are the required number of adjacent tiles in the same row, column, or diagonal
        for (int i=0;i <size;i++) {
            for (int j =0; j <size;j++) {
                if (gameBoard[i][j] == symbol) {
                    int horizontalcount = 0;	//this checks if a win is in the hoirzontal direction
                    for (int k =i; k <size;k++) {
                        if (gameBoard[k][j] ==symbol) {
                            horizontalcount++;
                        } else {
                            break;
                        }
                    }
                    if (horizontalcount >=tilesToWin)
                        return true;
                    int verticalcount =0;	//this checks if a win is in the vertical direction
                    for (int k = j; k <size; k++) {
                        if (gameBoard[i][k] == symbol) {
                            verticalcount++;
                        } else {
                            break;
                        }
                    }
                    if (verticalcount >=tilesToWin)
                        return true;
                }
                if (gameBoard[i][j]==symbol) {	//this checks if a win is in the diagonal direction
                    int tempi=i;	//creating temporary variables so that we don't affect i and j
                    int tempj=j;
                    //create temporary variables that can be modified without affecting i or j
                    int leftcounter=0;	//diagonal in the left direction
                    while ((tempj>=0) && (tempi <=size-1)) {
                        if (gameBoard[tempi][tempj]==symbol) {
                            leftcounter++;
                        }
                        else leftcounter =0;
                        if (leftcounter >=tilesToWin) return true;
                        else {
                            tempj--;
                            tempi++;
                        }
                    }
                }
                if (gameBoard[i][j] ==symbol) {	
                    int tempj =j;	
                    int tempi= i;
                    int rightcounter =0;	//diagonal in the right direction
                    while ((tempj <=size-1) && (tempi <=size-1)){
                        if (gameBoard[tempi][tempj] == symbol){
                            rightcounter++;
                        }
                        else rightcounter = 0;

                        if (rightcounter >= tilesToWin) return true;
                        else{
                            tempj++;
                            tempi++;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isDraw(){	//returns true if there are no empty positions left in gameBoard
        for (int i =0; i <size;i++){
            for (int j =0; j <size; j++){
                if (squareIsEmpty(i, j)){
                    return false;
                }
            }
        }
        return true;

    }

    public int evalBoard(){
        if (wins('c')) //computer win
            return 3;
        else if (wins('h')) //human win
            return 0;
        else if (isDraw()) //draw
            return 2;
        else
            return 1; //undecided
    }

}