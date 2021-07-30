import java.util.*;
import java.io.*;

public class coditation
{
	public final static int dead = 0;
	public final static int live = 1;
	
	public static void main(String args[])
	{
		coditation cobj = new coditation();
		
		Scanner sobj = new Scanner(System.in);		
		System.out.println("How many test case you want to perform:");
		int testcase = sobj.nextInt();
		cobj.test(testcase);
	}
	
	public void test(int testcase)
	{
		Scanner sobj = new Scanner(System.in);
		
		System.out.println("Enter the rows:");
		int irow = sobj.nextInt();
		
		System.out.println("Enter the columns:");
		int icol = sobj.nextInt();
		
		int board[][] = new int[irow][icol];
		int i=0,j=0;
		
		System.out.println("Enter the element in board:");
		for(i=0;i<board.length;i++)
		{
			for(j=0;j<board[i].length;j++)
			{
				board[i][j]=sobj.nextInt();
			}
		}
	
		System.out.println("Board :- ");
		printBoard(board);
		
		for(i=0;i<testcase;i++)
		{
			System.out.println();
			board = nextBoard(board);
			printBoard(board);
		}
	}
	
	public void printBoard(int board[][])
	{
		int i=0,j=0;
		
		for(i=0;i<board.length;i++)
		{	
			for(j=0;j<board[i].length;j++)
			{
				System.out.print(Integer.toString(board[i][j]) + "\t");
			}
			System.out.println();
		}
	}
	
	public int[][] nextBoard(int board[][])
	{
		int irow=board.length;
		int icol=board[0].length;
		
		int buf[][]=new int[irow][icol];
		int i=0,j=0;
		
		for(i=0;i<irow;i++)
		{
			for(j=0;j<icol;j++)
			{
				buf[i][j] = newCellState(board[i][j],liveNeighbours(i,j,board));
			}
		}
		
		return buf;
	}
	
	public int liveNeighbours(int irow,int icol,int board[][])
	{
		int rowEnd=Math.min(board.length, irow+2);
		int colEnd=Math.min(board[0].length, icol+2);
		
		int liveNeighbours=0;
		int i=0,j=0;
		
		for(i=Math.max(0, irow-1);i<rowEnd;i++)
		{
			for(j=Math.max(0, icol-1);j<colEnd;j++)
			{
				if((i!=irow || j!=icol) && (board[i][j]==live))
				{
					liveNeighbours++;
				}
			}
		}
		
		return liveNeighbours;
	}
	
	public int newCellState(int curState, int liveNeighbours)
	{
		int newState = curState;
		
		switch(curState)
		{
			case live:
					if(liveNeighbours<2)
					{
						newState=dead;
					}
					
					if(liveNeighbours==2 || liveNeighbours==3)
					{
						newState=live;
					}
					
					if(liveNeighbours>3)
					{
						newState=dead;
					}
					
					break;
					
			case dead:
					if(liveNeighbours==3)
					{
						newState=live;
					}
					
					break;
					
			default:
					System.out.println("State of cell must be either live or dead");
		}
		
		return newState;
	}
}