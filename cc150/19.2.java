enum Piece = {Empty, Red, Blue};

enum Check = {ROW, COLUMN, DIAGONAL, REVERSE_DIAGONAL};


public Piece getColor(Piece[][] board, int fix, int var, Check c) {	//得到对应棋格的棋子
	if(c==Check.ROW)
		return board[fix][var];
	if(c==Check.COLUMN)
		return board[var][fix];
	if(c==Check.DIAGONAL)
		return board[var][var];
	if(c==Check.REVERSE_DIAGONAL)
		return board[board.length-1-fix][var];
	return Piece.Empty;
}

public Piece getWinner(Piece[][] board, int fix, Check c) {		//check当前行列有没偶赢家
	Piece color = getColor(board, fix, 0, c);
	if(color==Piece.Empty)
		return Piece.Empty;

	for(int i=1; i<board.length; i++)
	{
		if(color!=getColor(board, fix, i, c))
			return Piece.Empty;
	}
	return color;
}


public Piece hasWon(Piece[][] board) {

	Piece winner = Piece.Empty;

	for(int i=0; i<board.length; i++)		//遍历一遍查每一行每一列有没有赢家
	{
		winner = getWinner(board, i, Check.ROW);
		if(winner!=Piece.Empty)
			return winner;

		winner = getWinner(board, i, Check.COLUMN);
		if(winner!=Piece.Empty)
			return winner;
	}

	winner = getWinner(board, 0, Check.DIAGONAL);		//check对角线和反对角线
	if(winner!=Piece.Empty)
		return winner;

	winner = getWinner(board, 0, Check.REVERSE_DIAGONAL)
		return winner;

	return Piece.Empty;
}