package hw2;
import hw1.*;
import java.util.Scanner;

public class SolveMazes {
    private static String moves = "";
    private static char[][] temp;
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String fileName = "";
        MazeSolver test = new MazeSolver();
        while(!fileName.equals("stop")){
            fileName = kb.nextLine();
            if(!fileName.equals("stop")){
                JaggedGridReader input = new JaggedGridReader(fileName);
                char[][] maze = input.getCopy();
                System.out.println("maze before: \n" + input.toString());
                if(test.solveMaze(maze)){
                    input.grid = test.getMaze();
                    System.out.println("maze after:\n" + input.toString());
                    System.out.println("Cells Visited: " + test.getNumCellsVisited());
                } else {
                    System.out.println("No Solution");
                }
                System.out.println("Percent Correct: " + (int)Math.round(test.getPerformance()) + "%");
            }
        }
        kb.close();
    }
}
