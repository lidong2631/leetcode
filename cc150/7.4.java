public abstract class ChessPieceBase {
	abstract boolean canBeChecked();
	abstract boolean isSupportCastle();
}

public class King extends ChessPieceBase {

}

other chess pieces like queen, soldier...


public class ChessPieceTurn {

}

public abstract class PlayerBase {
	public abstract ChessPieceTurn getTurn(Position p);
}


public class ComputerPlayer extends PlayerBase {
	public PositionEstimator estimator;
	public PositionBackTracker backtracker;

	public ChessPieceTurn getTurn(Position p) {

	}

	public void setDifficulty(int difficultyLevel) {

	}
}

public class HumanPlayer extends PlayerBase {
	public ChessPieceTurn getTurn(Position p) {

	}
}


public class Position {
	ArrayList<ChessPieceBase> black;
	ArrayList<ChessPieceBase> white;
}




public class PositionBackTracker {

}

public abstract class PositionPotentialValue {
	abstract boolean lessThan
}

public class PositionEstimator {
	public 
}

