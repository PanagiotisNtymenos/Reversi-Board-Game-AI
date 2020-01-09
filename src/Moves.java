public class Moves {
	
	static char temp;
	int maxEval,minEval,eval,finali,finalj,maxDepth;
	boolean foundMove;
	int counter;
	
	
	//Values that give weight to the moves. Corners are good while the neighbors not etc.
	public int[][] Values = new int[][]
	{
		{25,-3,11,8,8,11,-3,25},
    	{-3,-7,-4,1,1,-4,-7,-3},
    	{11,-4,2,2,2,2,-4,11},
    	{8,1,2,-3,-3,2,1,8},
    	{8,1,2,-3,-3,2,1,8},
    	{11,-4,2,2,2,2,-4,11},
    	{-3,-7,-4,1,1,-4,-7,-3},
    	{25,-3,11,8,8,11,-3,25}
	};
	
	
	//counts the number of the possible moves
	public int numOfmoves(Board B,char choice){
		counter=0;
		possibleMoves(B,choice);
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(B.Map[i][j]=='Z'){
					counter++;
				}
			}
		}
		return counter;
	}
	
	/*
	 * returns true if there is one or more moves to make else return false
	 * if We find a disc that is given in the parameter  (O or X). we check in all directions for a possible move
	 */
	public boolean possibleMoves(Board B, char OX){
		foundMove = false;
		char revOX='O';
		if(OX=='O'){
			revOX='X';
		}
		//initializing Map ,nextMoves and nextMoves.currentboard to be the same us caurrent
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				B.Map[i][j]=' ';
				B.nextMoves[i][j]=new Board();
				for(int x=0;x<8;x++){
					for(int y=0;y<8;y++){
						B.nextMoves[i][j].currentBoard[x][y]=B.currentBoard[x][y];
					}
				}
			}
		}
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(B.currentBoard[i][j]==OX){
					NorthWest(B,i,j,OX,revOX);
					North(B,i,j,OX,revOX);
					NorthEast(B,i,j,OX,revOX);
					West(B,i,j,OX,revOX);
					East(B,i,j,OX,revOX);
					SouthWest(B,i,j,OX,revOX);
					South(B,i,j,OX,revOX);
					SouthEast(B,i,j,OX,revOX);
				}
			}
		}
		return foundMove;
	}

	
	/* 
	 * First check if in the given direction we find at least one enemy disc
	 * If we find an enemy disc we check for an empty spot right after .
	 * So if we have Something like ( X,O,O,O,_) we can make the move and 
	 * we update the next move for the current spot .
	 * For Example if 7,2 is a solution, we construct the solution in Board.nextMoves[7][2]
	 * SAME FOR ALL DIRECTIONS JUST CHANGING THE DIRECTION WE SEARCH 
	 */
	public void SouthEast(Board B, int i, int j, char OX, char revOX) {
		try {
			boolean flag=false;
			int oldj=j;
			int oldi=i;
			while (B.currentBoard[i+1][j+1] == revOX) {
				j++;
				i++;
				flag=true;
			}
			j++;
			i++;
			if(flag&&B.currentBoard[i][j]== ' '){
				B.Map[i][j]='Z';
				foundMove = true;
				for(int temp=oldi;temp<=i;temp++){	
					B.nextMoves[i][j].currentBoard[temp][oldj]=OX;
					oldj++;
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
	}

	public void South(Board B, int i, int j, char OX, char revOX) {
		try {
			boolean flag=false;
			int oldi=i;
			while (B.currentBoard[i+1][j] == revOX) {
				i++;
				flag=true;
			}
			i++;
			if(flag&&B.currentBoard[i][j]== ' '){
				B.Map[i][j]='Z';
				foundMove = true;
				for(int temp=oldi;temp<=i;temp++)
				B.nextMoves[i][j].currentBoard[temp][j]=OX;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
	}

	public void SouthWest(Board B, int i, int j, char OX, char revOX) {
		try {
			boolean flag=false;
			int oldj=j;
			int oldi=i;
			while (B.currentBoard[i+1][j-1] == revOX) {
				j--;
				i++;
				flag=true;
			}
			j--;
			i++;
			if(flag&&B.currentBoard[i][j]== ' '){
				B.Map[i][j]='Z';
				foundMove = true;
				for(int temp=oldi;temp<=i;temp++)
				{	
					B.nextMoves[i][j].currentBoard[temp][oldj]=OX;
					oldj--;
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
	}

	public void East(Board B, int i, int j, char OX, char revOX) {
		try {
			boolean flag=false;
			int oldj=j;
			while (B.currentBoard[i][j+1] == revOX) {
				j++;
				flag=true;
			}
			j++;
			if(flag&&B.currentBoard[i][j]== ' '){
				B.Map[i][j]='Z';
				foundMove = true;
				for(int temp=oldj;temp<=j;temp++)
				B.nextMoves[i][j].currentBoard[i][temp]=OX;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
	}

	public void West(Board B, int i, int j, char OX, char revOX) {
		try {
			boolean flag=false;
			int oldj=j;
			while (B.currentBoard[i][j-1] == revOX) {
				j--;
				flag=true;
			}
			j--;
			if(flag&&B.currentBoard[i][j]== ' '){
				B.Map[i][j]='Z';
				foundMove = true;
				for(int temp=oldj;temp>=j;temp--)
					B.nextMoves[i][j].currentBoard[i][temp]=OX;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
	}

	public void NorthEast(Board B, int i, int j, char OX, char revOX) {
		try {
			boolean flag=false;
			int oldj=j;
			int oldi=i;
			while (B.currentBoard[i-1][j+1] == revOX) {
				j++;
				i--;
				flag=true;
			}
			j++;
			i--;
			if(flag&&B.currentBoard[i][j]== ' '){
				B.Map[i][j]='Z';
				foundMove = true;
				for(int temp=oldi;temp>=i;temp--){
					B.nextMoves[i][j].currentBoard[temp][oldj]=OX;
					oldj++;
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
	}

	public void North(Board B, int i, int j, char OX, char revOX) {
		try {
			boolean flag=false;
			int oldi=i;
			while (B.currentBoard[i-1][j] == revOX) {
				i--;
				flag=true;
			}
			i--;
			if(flag&&B.currentBoard[i][j]== ' '){
				B.Map[i][j]='Z';
				foundMove = true;
				for(int temp=i;temp<=oldi;temp++)
					B.nextMoves[i][j].currentBoard[temp][j]=OX;
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
	}

	public void NorthWest(Board B, int i, int j, char OX, char revOX) {
		try {
			boolean flag=false;
			int oldj=j;
			int oldi=i;
			while (B.currentBoard[i-1][j-1] == revOX) {
				flag=true;
				j--;
				i--;
			}
			j--;
			i--;
			if(B.currentBoard[i][j]== ' '&&flag) {
				B.Map[i][j]='Z';
				foundMove = true;
				for(int temp=oldi;temp>=i;temp--) {
					B.nextMoves[i][j].currentBoard[temp][oldj]=OX;
					oldj--;
				}
			}
		} catch(ArrayIndexOutOfBoundsException e) {}
	}
	
	public boolean first;
	
	//A function Caller of minimax. keeping some info saved that may alter in minimax.
	//Finally returns the Board with the move AI played
	public Board outcomeminimax(Board B, int depth, int alpha, int beta, char choice,char revchoice, boolean maximizingPlayer){
		maxDepth=depth;
		first=true;
		minimax(B,depth,alpha,beta,choice,revchoice,maximizingPlayer);
		System.out.println("CPU played: " + (finali+1) + ", " + (finalj+1));
		return B.nextMoves[finali][finalj];
	}
	
	boolean breakflag=false;//Technically it is a break for the nested for
	
	//minimax algorithm with alpha beta pruning returns an int value but also finali and finalj stores the tables position that 
	// has the best move for the AI. It is a strange tree that starts with max depth and goes towoards 0. 
	//(evaluation in depth 0 and in Game over situations)
	public int minimax(Board B, int depth, int alpha, int beta, char choice, char revchoice, boolean maximizingPlayer){
		if (depth==0) return evaluation(B,choice,revchoice);
		if(maximizingPlayer){
			maxEval=-100000;
			if(possibleMoves(B,choice)){
				for(int i=0;i<8;i++){
					if(breakflag){
						break;	
					}
					for(int j=0;j<8;j++){
						if(B.Map[i][j]=='Z'){
							if(depth==maxDepth&&first){
								finali=i;
								finalj=j;
								first=false;
							}
							eval=minimax(B.nextMoves[i][j],depth-1,alpha,beta,choice,revchoice,false);
							if(maxEval<eval){
								maxEval=eval;		
							}
							if(alpha<eval){
								alpha=eval;
								if(depth==maxDepth){
									finali=i;
									finalj=j;
								}
							}
							if (beta<=alpha){
								breakflag=true;
								break;
							}
							breakflag=false;
						}
					}
				}
			} else {
				if(!possibleMoves(B,revchoice)){
					return	GameOver(B,choice,revchoice);
				} else {
					eval=minimax(B,depth-1,alpha,beta,choice,revchoice,false);
					if(maxEval<eval){
						maxEval=eval;							
					}
					if(alpha<eval){
						alpha=eval;
					}
				}
			}
			return maxEval;
		} else {
			minEval=100000;
			if(possibleMoves(B,revchoice)){
				for(int i=0;i<8;i++){
					if(breakflag){
						break;
					}
					for(int j=0;j<8;j++){
						if(B.Map[i][j]=='Z'){
							eval=minimax(B.nextMoves[i][j],depth-1,alpha,beta,choice,revchoice,true);
							if(minEval>eval){
								minEval=eval;
							}
							if(beta>eval){
								beta=eval;
							}
							if (beta<=alpha) {
								breakflag=true;
								break;
							}
							breakflag=false;
						}
					}
				}
			}
			else{
				if(!possibleMoves(B,choice)){
					return GameOver(B,choice,revchoice);
				} else {
					eval=minimax(B,depth-1,alpha,beta,choice,revchoice,true);
					if(minEval>eval){
						minEval=eval;
					}
					if(beta>eval){
						beta=eval;
					}
				}
			}
			return minEval;
		}
	}

	
	//Evaluation method in Game Over Situations . So if we win we give a very great value 
	//If we lose we try not to choose the current board so we give a very bad value 
	//generally used only in situations that minimax sees that we wil have a terminal situation
	private int GameOver(Board B, char choice, char revchoice) {
		int valueChoice=0;
		int valueRevCh=0;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if (B.currentBoard[i][j] == choice){
					valueChoice ++;
				}
				else if(B.currentBoard[i][j] == revchoice){
					valueRevCh++;
				}
				
			}
		}
		if(valueChoice>valueRevCh){
			return 9999;
		} else if(valueChoice<valueRevCh){
			return -9999;
		} else return 0;//Tie is neutral
	}

	/*
	 * Our Evaluation method is a combination of 4 heuristic functions
	 * 1. Corners: We give great values to the corners because generally they help you to get the game
	 * 2. Corners closeness: It is generally good to force the opponent to get the positions next to corners
	 * So if we don't have the corner and we get the opponent to take one tile next to it we may get it
	 * 3.Weighted Values: Using the value table We can determine if the position we are now is good or bad 
	 * evaluating each tile on the table (positive for the good ones and negative for the bad)
	 * 4.Number of Tiles: How many tiles do we have in comparison to the opponent
	 * 
	 * OUR HEURISTIC : Combine all of them . Always take into consideration and the opponents position. 
	 * Corner is very important > Closeness is also important > Values too > Number of tiles is almost useless to make decision
	 * So each heuristic is taken in account in different rate.
	 */
	private int evaluation(Board B, char choice,char revchoice) {
		int AI_discs = 0;
		int Player_discs = 0;
		if(B.currentBoard[0][0] == choice) AI_discs++;
		else if(B.currentBoard[0][0] == revchoice) Player_discs++;
		if(B.currentBoard[0][7] == choice) AI_discs++;
		else if(B.currentBoard[0][7] == revchoice) Player_discs++;
		if(B.currentBoard[7][0] == choice) AI_discs++;
		else if(B.currentBoard[7][0] == revchoice) Player_discs++;
		if(B.currentBoard[7][7] == choice) AI_discs++;
		else if(B.currentBoard[7][7] == revchoice) Player_discs++;
		int corners =  (AI_discs - Player_discs);

		// Corner closeness
		AI_discs = 0;
		Player_discs = 0;
		if(B.currentBoard[0][0] == ' ')   {
			if(B.currentBoard[0][1] == choice) AI_discs++;
			else if(B.currentBoard[0][1] == revchoice) Player_discs++;
			if(B.currentBoard[1][1] == choice) AI_discs++;
			else if(B.currentBoard[1][1] == revchoice) Player_discs++;
			if(B.currentBoard[1][0] == choice) AI_discs++;
			else if(B.currentBoard[1][0] == revchoice) Player_discs++;
		}
		if(B.currentBoard[0][7] == ' ')   {
			if(B.currentBoard[0][6] == choice) AI_discs++;
			else if(B.currentBoard[0][6] == revchoice) Player_discs++;
			if(B.currentBoard[1][6] == choice) AI_discs++;
			else if(B.currentBoard[1][6] == revchoice) Player_discs++;
			if(B.currentBoard[1][7] == choice) AI_discs++;
			else if(B.currentBoard[1][7] == revchoice) Player_discs++;
		}
		if(B.currentBoard[7][0] == ' ')   {
			if(B.currentBoard[7][1] == choice) AI_discs++;
			else if(B.currentBoard[7][1] == revchoice) Player_discs++;
			if(B.currentBoard[6][1] == choice) AI_discs++;
			else if(B.currentBoard[6][1] == revchoice) Player_discs++;
			if(B.currentBoard[6][0] == choice) AI_discs++;
			else if(B.currentBoard[6][0] == revchoice) Player_discs++;
		}
		if(B.currentBoard[7][7] == ' ')   {
			if(B.currentBoard[6][7] == choice) AI_discs++;
			else if(B.currentBoard[6][7] == revchoice) Player_discs++;
			if(B.currentBoard[6][6] == choice) AI_discs++;
			else if(B.currentBoard[6][6] == revchoice) Player_discs++;
			if(B.currentBoard[7][6] == choice) AI_discs++;
			else if(B.currentBoard[7][6] == revchoice) Player_discs++;
		}
		int closeness = -1 * (AI_discs - Player_discs);
		int chdiscs=0;
		int revdiscs=0;
		int valuech=0;
		int valuerevch=0;
		int Weightvalue;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if (B.currentBoard[i][j] == choice){
					valuech = Values[i][j] + valuech;
					chdiscs++;
				}
				else if(B.currentBoard[i][j] == revchoice){
					valuerevch = Values[i][j] + valuerevch;
					revdiscs++;
				}
			}
		}
		int Moretiles=chdiscs-revdiscs;
		Weightvalue= valuech-valuerevch;
		int eval= ((int)(10*Weightvalue + 250*corners + 100*closeness + Moretiles));
		return eval;
	}
	
}