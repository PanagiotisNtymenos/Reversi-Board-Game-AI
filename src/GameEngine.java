import java.util.Scanner;

public class GameEngine {
	
	static String tempchoice;
	static Scanner input = new Scanner(System.in);
	public static int row, column, endGame=0, Xnum=0, Onum=0;		
	static Moves move = new Moves();
	static Board z = new Board();
	static char revChoice = 'O';
	
	
	public static void main(String[] args){
		System.out.println();
		System.out.println("~~~~~~~~~~~~~~~~ Welcome to Reversi! ~~~~~~~~~~~~~~~~ ");
		System.out.println();
		//giving the choice to play first or second
		System.out.println("Choose X if you want to play first or O if you want to play second: ");
		tempchoice =input.nextLine();
		//Input must be O or X
		while (!(tempchoice.equals("X") || tempchoice.equals("O"))) {
			System.out.println("Wrong input, please choose between X and O!");
			tempchoice =input.nextLine();
		}
		char choice=tempchoice.charAt(0);
		if (choice=='O'){
			revChoice='X';
			AITurn(revChoice,choice);
		}
		//EndGame is a counter . If two players in a row cannot play we have End of Game 
		while(endGame < 2) {
			playersTurn(choice);	
			if (endGame<2){
				AITurn(revChoice,choice);
			}
			if(endGame >= 2){
				break;
			}
		}
		calculateScore(z, choice);
		input.close();
	}
	
	//Helpful function that returns if an Input form keyboard is Integer or Not
	public static boolean isInteger(String input) {
	    try {
	        Integer.parseInt(input);
	        return true;
	    }
	    catch( Exception e ) {
	        return false;
	    }
	}
	
	boolean giveandcolumn;//give column only if row is integer
	/*
	 * Player's turn first print the choice then calculate the possible moves
	 * Print the board with the possible moves to help player choose
	 * We make sure that the inputs are valid (Integers are valid for move) 
	 * If input is mismatched we ask again for Integers
	 * then we replace current board with the next move that the player chose to make
	 */
	public static void playersTurn(char choice){
		boolean giveandcolumn=false;
		System.out.println("Player's turn("+choice+"): ");
		if(move.possibleMoves(z,choice)){
			row = 0;
			column = 0;
			endGame=0;
			String testint;
			z.printWithMap(z);
			
			System.out.print("Enter row value: ");
			testint= input.next();
			if(isInteger(testint)){
				giveandcolumn=true;
				row=Integer.parseInt(testint);
			} else row=-1;
			
			if(giveandcolumn){
				System.out.print("Enter column value: ");
				testint= input.next();
				if(isInteger(testint)) column=Integer.parseInt(testint);
				else column=-1;
			}
			System.out.println();
			
			while(true){
				giveandcolumn=false;
				if(row > 0 && row < 9 && column > 0 && column < 9)
					if (z.Map[row-1][column-1]=='Z')
						break;
					else System.out.println("No move in such coordinates. Try again.");
				else System.out.println("Wrong input given. Try again.");
				System.out.print("Enter row value: ");
				testint= input.next();
				if(isInteger(testint)){
					giveandcolumn=true;
					row=Integer.parseInt(testint);
				} else {
					row=-1;
				}
				if(giveandcolumn){
					System.out.print("Enter column value: ");
					testint= input.next();
					if(isInteger(testint)) column=Integer.parseInt(testint);
					else column=-1;
				}
			}
			for(int x=0;x<8;x++){
				for(int y=0;y<8;y++){
					z.currentBoard[x][y]= z.nextMoves[row-1][column-1].currentBoard[x][y];
				}
			}
		} else {
			System.out.println("No moves to make.");
			endGame++;
		}
	}
	
	
	/*
	 * Print the symbol of CPU check  if AI can make a move 
	 * and if AI can move we call the outcomeminimax and then replace
	 * the board with the AI's choice
	 * 
	 *                 ~~~~~~DEFAULT VALUE FOR MINIMAX DEPTH:5~~~~~~
	 *                (Can work for depth 7 but with a little latency)
	 */
	public static void AITurn(char choice, char revChoice){
		System.out.println("CPU's turn("+choice+"): ");
		if(move.possibleMoves(z,choice)){
			endGame=0;
			Board temp = move.outcomeminimax(z, 5, -10000, 10000, choice, revChoice, true) ;
			for(int x=0;x<8;x++){
				for(int y=0;y<8;y++){
					z.currentBoard[x][y]= temp.currentBoard[x][y];
				}
			}
		} else {
			System.out.println("No moves to make.");
			endGame++;
		}
	}
	
	/*
	 * calculates the score and also 
	 * gives the last board that we have the Game Over
	 * also prints the score of each player
	 */
	public static void calculateScore(Board B, char choice) {
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(z.currentBoard[i][j]=='X')Xnum++;
				else if(z.currentBoard[i][j]=='O')Onum++;
			}
		}
		B.print(B);
		if(Xnum>Onum){
			if(choice == 'X')System.out.println("You Won! Score: "+Xnum+" - "+Onum);
			else System.out.println("You Lost! Score: " + Xnum + " - "+Onum);
		}	
		else if(Xnum<Onum){
			if(choice == 'O') System.out.println("You Won! Score: "+Xnum+" - "+Onum);
			else System.out.println("You Lost! Score: " + Xnum + " - "+Onum);
		}
		else System.out.println("Draw! Score:"+Xnum+" - "+Onum);
	}
	
}