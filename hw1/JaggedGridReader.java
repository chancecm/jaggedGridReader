package hw1;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class JaggedGridReader {
    public char[][] grid;
    String fileName;
    public static void main(String[] args) {

    }
    public JaggedGridReader(String fileName){
        this.fileName = fileName;
        Scanner kb;
        kb = new Scanner(System.in);
        File file = new File(fileName);

        try {
            kb = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }

        int rows = 0;
        while (kb.hasNextLine()) {
            rows++;
            kb.nextLine();
        }
        kb.close();

        try {
            kb = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }

        char[][] grid = new char[rows][];
        int row = 0;
        while (kb.hasNextLine()) {
            String line = kb.nextLine();
            grid[row] = line.toCharArray();
            row++;
        }
        kb.close();

        this.grid = grid;
    }
    public String toString() {
        String gridString = "";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                gridString += grid[i][j];
            }
            gridString += "\n";
        }
        return gridString;
    }
    public char[][] getCopy() {
        char[][] copy = new char[grid.length][];

        for (int row = 0; row < grid.length; row++) {
            int rowLength = grid[row].length;
            copy[row] = new char[rowLength];
            for (int col = 0; col < rowLength; col++) {
                copy[row][col] = grid[row][col];
            }
        }
        return copy;
    }

    public String copyString(){
        String gridString = "";
        for (int i = 0; i < getCopy().length; i++) {
            for (int j = 0; j < getCopy()[i].length; j++) {
                gridString += getCopy()[i][j];
            }
            gridString += "\n";
        }
        return gridString;
    }
    public String getFileName() {

        return fileName;
    }
}
