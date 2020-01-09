public class Board {
	
	public char[][] Map = new char[8][8];//Holds the game's board with the possible moves of one player.
	public char[][] currentBoard = new char[8][8];//The actual board of the game
	/*In each possible move we can save the next boards (next moves)
 	working like a hashmap,decreasing the time of searching(We lose in terms of memory) */
	public Board[][] nextMoves;
	
	//default constructor (creating the starting table with ' ' and O X in the middle positions)
	public Board() {
		nextMoves = new Board[8][8];
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				currentBoard[i][j]=' ';
			}
		}
		currentBoard[3][3]= 'O';
		currentBoard[3][4]= 'X';
		currentBoard[4][3]= 'X';
		currentBoard[4][4]= 'O';
	}
	
	//Prints the current board
	public void print(Board x) {
		System.out.print("  ");
		for(int j=0;j<8;j++){
			System.out.print(j+1+" ");
		}
		System.out.println();
		for(int i=0; i<8; i++){
			System.out.print(i+1);
			System.out.print('|');
			for(int j=0;j<8;j++){
				System.out.print(x.currentBoard[i][j]);
				System.out.print('|');
			}
			System.out.println();
		}
	}
	
	//print the current board with the possible next moves 
	public void printWithMap(Board x) {
		System.out.print("  ");
		for(int j=0;j<8;j++){
			System.out.print(j+1+" ");
		}
		System.out.println();
		for(int i=0; i<8; i++){
			System.out.print(i+1);
			System.out.print('|');
			for(int j=0;j<8;j++){
				if(x.Map[i][j]=='Z'){
					System.out.print("~");
				} else {
					System.out.print(x.currentBoard[i][j]);
				}
				System.out.print('|');
			}
			System.out.println();
		}
	}
	
}