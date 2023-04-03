package hw2;
import java.util.ArrayList;

public class MazeSolver {
    // Initialize constant characters to represent different maze cell types
    private char OPEN = '.';
    private char BLOCKED = '#';
    private char START = 'S';
    private char GOAL = 'G';
    private char MARKED = '+';
    private char UNMARKED = 'x';
    private char[][] maze; // 2D array to store the maze
    private ArrayList<String> mazePath = new ArrayList<>(); // List to store the path taken to solve the maze
    private int numCellsVisited; // Counter to track number of cells visited in the maze
    private int mazesSolved; // Counter to track number of mazes solved
    private int mazesTried; // Counter to track number of mazes attempted
    public boolean solveMaze(char[][] maze){
        mazePath.clear();
        this.maze = maze; // Set the input maze as the instance variable maze
        int startRow = 0; // Initialize the starting row
        int startCol = 0; // Initialize the starting column
        for(int row = 0; row < maze.length; row++){ // Loop through rows of the maze
            for(int col = 0; col < maze[0].length; col++){ // Loop through columns of the maze
                if(maze[row][col] == START){ // If the current cell is the starting cell
                    startRow = row; // Set the starting row to the current row
                    startCol = col; // Set the starting column to the current column
                }
                if(maze[row][col] == MARKED || maze[row][col] == UNMARKED){ // If the current cell is MARKED or UNMARKED
                    maze[row][col] = UNMARKED; // Set the current cell to UNMARKED
                }
            }
        }
        numCellsVisited = 0; // Reset the number of cells visited
        boolean pathFound = findPath(startRow, startCol); // Attempt to find a path from the starting cell
        if(pathFound){ // If a path was found
            mazesSolved++; // Increment the number of mazes solved
        }else{
            mazePath.clear(); // Clear the maze path
        }
        mazesTried++; // Increment the number of mazes tried
        return pathFound; // Return whether a path was found or not
    }
    private boolean findPath(int x, int y){
        // Check if the current cell is blocked or already marked.
        if(maze[x][y] == BLOCKED || maze[x][y] == MARKED){
            return false;
        }
        // Check if the current cell is the goal.
        if(maze[x][y] == GOAL){
            return true;
        }
        // Mark the current cell as visited.
        char prev = maze[x][y];
        maze[x][y] = MARKED;
        numCellsVisited++;
        // Recursively search for a path in each of the four directions,
        // starting with the North direction.
        if(x > 0 && findPath(x - 1, y)){
            mazePath.add("North");
            return true;
        }
        // If no path is found in the North direction, search in the West direction.
        if(y > 0 && findPath(x, y - 1)){
            mazePath.add("West");
            return true;
        }
        // If no path is found in the West direction, search in the South direction.
        if(x < maze.length - 1 && findPath(x + 1, y)){
            mazePath.add("South");
            return true;
        }
        // If no path is found in the South direction, search in the East direction.
        if(y < maze[0].length - 1 && findPath(x, y + 1)){
            mazePath.add("East");
            return true;
        }
        // If no path is found in any direction, unmark the current cell and return false.
        if(maze[x][y] == MARKED){
            maze[x][y] = UNMARKED;
        }
        return false;
    }
    public String[] getMoves(){
        // Check if the mazePath ArrayList is empty.
        if (mazePath == null || mazePath.isEmpty()){
            return null;
        }
        String[] moves = new String[mazePath.size()]; // Create String array moves.
        // Loop through the elements of the moves array.
        for(int i = 0; i < moves.length; i++){
            // Store elements from mazePath ArrayList inside moves String array.
            moves[i] = mazePath.get(mazePath.size() - i - 1);
        }
        return moves;
    }
    public char[][] getMaze(){
        // Return 2D char array maze after recursion in findPath() method.
        return maze;
    }
    public int getNumCellsVisited(){
        // Return the number of cells visited before reaching GOAL.
        return numCellsVisited;
    }
    public double getPerformance(){
        // Return ratio of mazesSolved to mazesTried and cast it as a double.
        return ((double)mazesSolved / mazesTried)*100;
    }
}
