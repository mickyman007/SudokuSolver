package Sudoku;
import java.util.*;
/**
 * Created by Ben on 20/06/2016.
 *
 * Sudoku Rules:
 * - Must be no duplicate number in each row
 * - Must be no duplicate number in each column
 * - Must be no duplicate number in each grid (3x3)
 */
public class Sudoku
{
    /* Sample sudoku table 9x9 */
    public static int[][] table = {
            {0, 1, 2, 0, 0, 0, 0, 7, 9},
            {0, 0, 9, 8, 3, 0, 0, 4, 0},
            {5, 0, 0, 0, 0, 7, 0, 0, 8},
            {1, 0 ,0, 0, 6, 5, 9, 0, 3},
            {0, 0, 5, 3, 7, 0, 1, 0, 0},
            {0, 3, 0, 0, 0, 0, 2, 0, 0},
            {0, 0, 0, 5, 9, 4, 0, 0, 0},
            {9, 2, 0, 0, 8, 0, 0, 0, 0},
            {4, 0, 0, 0, 0, 3, 0, 0, 6}
    };

    public static void main(String[] args)
    {
        System.out.println(solve());
        System.out.println();
        printSudoku();
    }//main
    
    /* Executes method to go through sudoku table, params are simply starting position */
    private static int solve(){
        return countThrough(0, 0);
    }

    /* Prints the sudoku table (2D Array) */
    private static void printSudoku(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(table[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /* Check if number already exists in row - r. We move along columns with i, while staying on same row. 
    c - current position in static table 
    num - num being tested */
    private static int rowCheck(int r, int c, int num){
        /* c (column) is where the tested value is in the table, if we get there, simply break check 
        e.g. If we inserted 5 into position 2 on table, skip or break that check, since it will return false and say there
        are two of the same number on the same row (sudoku rule) */
        for(int i = 0; i < 9; i++)
        {
            //skip check if equal to the position in contemporary table (to prevent duplicate returning 0)
            if(i == c && i < 8){
                i++;
            }

            //reached end of row
            if(i == c && c == 8){
                break;
            }
            
            //if table already has the number being tested, return false (sudoku rule)
            if (table[r][i] == num){
                return 0;
            }
        }
        return 1;
    }

    //r - row - current position in static table
    //c - column
    //num - number being tested
    //we move down by incrementing row with i, staying in same column
    private static int colCheck(int r, int c, int num){
        for(int i = 0; i < 9; i++)
        {
            //skip check if equal to the position in contemporary table
            if(i == r && i < 8){
                i++;
            }

            //reached end of column
            if(i == r && i == 8){
                break;
            }
            
            //if table already has the tested number in the column, return false (sudoku rule)
            if (table[i][c] == num)
            {
                return 0;
            }
        }
        return 1;
    }

    private static int panelCheck(int r, int c, int rmod, int cmod, int num){
        //Check row location, count from c -> c + 3
        for(int i = cmod; i < cmod+3; i++){
            //duplicate check - skips values from current table being tested
            if(i == c && i < 8){
                i++;
            }
            //check after increment that loop condition is still true
            if(i >=cmod+3){
                break;
            }
            //duplicate check - skips values from current table being tested
            if(i == c && i == 8){
                break;
            }

            if (table[rmod][i] == num)
            {
                return 0;
            }
        }//for

        //Go down one row
        rmod = rmod + 1;

        for(int i = cmod; i < cmod+3; i++){
            //duplicate check - skips values from current table being tested
            if(i == c && i < 8){
                i++;
            }
            //check after increment that loop condition is still true
            if(i >=cmod+3){
                break;
            }
            //duplicate check - skips values from current table being tested
            if(i == c && i == 8){
                break;
            }

            if (table[rmod][i] == num)
            {
                return 0;
            }
        }//for

        //Go down one row
        rmod = rmod + 1;

        for(int i = cmod; i < cmod+3; i++){
            //duplicate check - skips values from current table being tested
            if(i == c && i < 8){
                i++;
            }
            //check after increment that loop condition is still true
            if(i >=cmod+3){
                break;
            }
            //duplicate check - skips values from current table being tested
            if(i == c && i == 8){
                break;
            }

            if (table[rmod][i] == num)
            {
                return 0;
            }
        }//for
        return 1;
    }

    //r = row position
    //c = column position
    private static int countThrough(int r, int c){
        //Skip all non 0
        while(r < 9 && table[r][c] != 0){
            c++;
            if(c == 9){
                r++;
                c = 0;
            }
        }

        //Base case
        if(r == 9){
            return 1;
        }

        //i = number to insert into sudoku table
        for(int i = 1; i <= 9; i++){
            //set current position to number being tested
            table[r][c] = i;
            if(rowCheck(r, c, i) == 1 && colCheck(r, c, i) == 1 && panelCheck(r, c, r-r%3, c-c%3, i) == 1 && countThrough(r, c) == 1){
                table[r][c] = i;
                return 1;
            }
        }
        //Unsuccessful, reset element
        table[r][c] = 0;
        return 0;
    }
}//class
