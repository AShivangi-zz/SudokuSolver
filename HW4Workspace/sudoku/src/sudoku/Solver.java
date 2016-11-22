package sudoku;

import java.util.*;


public class Solver 
{
	private Grid						problem;
	private ArrayList<Grid>				solutions;
	
	
	public Solver(Grid problem)
	{
		this.problem = problem;
	}
	
	
	public void solve()
	{
		solutions = new ArrayList<>();
		solveRecurse(problem);
	}
	
		
	// 
	// FINISH THIS.
	//
	// Standard backtracking recursive solver.
	//
	private void solveRecurse(Grid grid)
	{		
		Evaluation eval = evaluate(grid);
		
		if (eval == Evaluation.ABANDON)
		{
			return;
		}
		else if (eval == Evaluation.ACCEPT)
		{
			solutions.add(grid);
		}
		else
		{
			ArrayList<Grid> nextNine = grid.next9Grids();
			for(int i = 0; i < nextNine.size(); i++) {
				solveRecurse(nextNine.get(i));
			}
		}
	}
	
	//
	// COMPLETE THIS
	//
	// Returns Evaluation.ABANDON if the grid is illegal. 
	// Returns ACCEPT if the grid is legal and complete.
	// Returns CONTINUE if the grid is legal and incomplete.
	//
	public Evaluation evaluate(Grid grid)
	{
		if (grid.isLegal() && grid.isFull()) {
			return Evaluation.ACCEPT;
		}

		else if (!grid.isLegal()) {
			return Evaluation.ABANDON;
		}

		else {
			return Evaluation.CONTINUE;
		}

	}

	
	public ArrayList<Grid> getSolutions()
	{
		return solutions;
	}
	
	
	public static void main(String[] args)
	{
		Grid g = TestGridSupplier.getPuzzle1();		
		Solver solver = new Solver(g);
		solver.solve();
		for(Grid grid: solver.getSolutions()){
		System.out.println("Solution found!");
		System.out.println(grid);
		if(grid.equals(TestGridSupplier.getSolution1())){
			
		
		System.out.println("The solution matches the given solution\n" + TestGridSupplier.getSolution1());
		}
		else{
			System.out.println("The solution does not match the given solution\n" + TestGridSupplier.getSolution1());
		}
		
		}
		
		// Print out your solution, or test if it equals() the solution in TestGridSupplier.
	}
}