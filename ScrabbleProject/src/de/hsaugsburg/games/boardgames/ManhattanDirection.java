package de.hsaugsburg.games.boardgames;

public enum ManhattanDirection {
	
	DOWN(1, 0) {
		public ManhattanDirection orthogonalDir() {
			return LEFT;
		}
	},
	UP(-1, 0) {
		public ManhattanDirection orthogonalDir() {
			return RIGHT;
		}
	},
	RIGHT(0, 1) {
		public ManhattanDirection orthogonalDir() {
			return DOWN;
		}
	},
	LEFT(0, -1) {
		public ManhattanDirection orthogonalDir() {
			return UP;
		}
	};
	
	public GridPoint gp;
	
	private ManhattanDirection(int row, int column) {
		gp = new GridPoint(row, column);
	}
	
	public static ManhattanDirection calcDir(GridPoint a, GridPoint b) {
		if (a.minus(b).getColumn() == 0) {
			return DOWN;
		} else if (a.minus(b).getRow() == 0) {
			return RIGHT;
		} else {
			return null;
		}
	}
	
	public ManhattanDirection orthogonalDir() {
		return null;
	}
	
}
