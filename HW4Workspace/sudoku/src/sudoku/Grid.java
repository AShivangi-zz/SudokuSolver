package sudoku;

import java.util.*;

public class Grid {
	private int[][] values;
	private int nCellsPerSide=9;


	public Grid(String[] rows) {
		values = new int[9][9];
		for (int j = 0; j < 9; j++) {
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i = 0; i < 9; i++) {
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}

	//constructor to take in values[][]
	public Grid(int[][] board) {
        values = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                this.values[row][column] = board[row][column];
            }
        }
    }


	public String toString() {
		String s = "";
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char) ('0' + n);
			}
			s += "\n";
		}
		return s;
	}



	// Finds an empty member of values[][]. Returns an array list of 9 grids that look like the current grid,
	// except the empty member contains 1, 2, 3 .... 9. Returns null if the current grid is full.
	//
	// Example: if this grid = 1........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//
	// Then the returned array list would contain:
	//
	// 11.......          12.......          13.......          14.......    and so on     19.......
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	//

	public ArrayList<Grid> next9Grids()

	{

		boolean flag= false;
		ArrayList<Grid> g2 = new ArrayList<Grid>();
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				while (values[j][i] == 0 && !flag) {
					for (int x = 1; x <= 9; x++) {
						values[j][i] = x;
						Grid temp = new Grid(values);
						g2.add(temp);
					}
					flag = true;
				}
			}

		}

		return g2;

	}

	//
	// Returns true if the input list contains a repeated value that isn't zero.
	// Call this from isLegal().
	//
	private boolean containsNonZeroRepeats(ArrayList<Integer> ints)
	{
		for(int i = 0; i < ints.size(); i++) {
			for(int j = i+1; j < ints.size(); j++) {
				if(ints.get(i) == ints.get(j) && ints.get(i) != 0) {
					return true;
				}
			}
		}
		return false;
	}

	

	//
	// Returns true if this grid is legal. A grid is legal if no row, column, or zone contains
	// a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	//
	public boolean isLegal() {

		//checking rows
		
		for(int i = 0; i < this.nCellsPerSide; i++) {
			ArrayList<Integer> rowValues = new ArrayList<Integer>();
			for(int j = 0; j < this.nCellsPerSide; j++) {
				rowValues.add(values[i][j]);
			}
			if(containsNonZeroRepeats(rowValues)) 
				return false;
		}

		//checking columns
		
		for(int i = 0; i < this.nCellsPerSide; i++) {
			ArrayList<Integer> columnValues = new ArrayList<Integer>();
			for(int j = 0; j < this.nCellsPerSide; j++) {
				columnValues.add(values[j][i]);
			}
			if(containsNonZeroRepeats(columnValues))
				return false;
		}
		

		//checking zones
		for (int x = 0; x <= 6; x += 3) {
			for (int y = 0; y <= 6; y += 3) {
				ArrayList<Integer> zoneValues = new ArrayList<Integer>();
				for (int i = x; i < x + 3; i++) {
					for (int j = y; j < y + 3; j++) {
						zoneValues.add(values[i][j]);
					}
				}
				if (containsNonZeroRepeats(zoneValues)) {
					return false;
				}
			}

		}
		return true;
	}


	
	// Returns true if every cell member of values[][] is a digit from 1-9.
		
	public boolean isFull() {
		   for (int i = 0; i < 9; i++) {
	            for (int j = 0; j < 9; j++) {
	                if (values[i][j] == 0) {
	                    return false;
	                }
	            }
	        }
	        return true;

	}

	// Returns true if x is a Grid and, for every (i,j), 
		// x.values[i][j] == this.values[i][j].
	public boolean equals(Object x) {

		Grid two = (Grid)x;
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[0].length; j++) {
                if (two.values[i][j] == this.values[i][j]) {
                    return true;
                }
            }
        }
    
    return false;
}

	}