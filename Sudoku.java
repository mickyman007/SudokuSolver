package Sudoku;
import java.util.*;
/**
 * Created by Ben on 20/06/2016.
 * More comments incoming soon tm
 */
public class Sudoku
{
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

    private static void printSudoku(){

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(table[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private static int rowCheck(int r, int c, int num){

        for(int i = 0; i < 9; i++)
        {
            //skip row check if equal to the row in contemporary table ( to prevent duplicate returning 0
            if(i == c && i < 8){
                i++;
            }

            //c is where the tested value is in the table, if we get there, simply break check
            if(i == c && c == 8){
                break;
            }
            if (table[r][i] == num)
            {
                return 0;
            }
        }
        return 1;
    }

    private static int colCheck(int r, int c, int num){

        for(int i = 0; i < 9; i++)
        {
            //skip col check if equal to the row in contemporary table ( to prevent duplicate returning 0
            if(i == r && i < 8){
                i++;
            }

            //r is where the tested value is in the table, if we get there, simply break check
            if(i == r && i == 8){
                break;
            }

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

    private static int solve(){
        return countThrough(0, 0);
    }
}//class
