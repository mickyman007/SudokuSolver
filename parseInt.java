import java.util.Scanner;

/**
 * Created by dickeyb on 20/10/2016.
 * Basic process of how a string to int conversion works
 */
public class parseInt {
    public static void main(String[] args){
        //Take input (number to be converted) from user
        Scanner scan = new Scanner(System.in);
        //Assign Input to a string
        String toConvert = scan.nextLine();

        //Call method to parse string as an int
        convertToInt(toConvert);
    }

    private static void convertToInt(String s){
        //Store values of converted strings
        int num = 0;
        int[] storeNumbers = new int[10];

        for(int counter = 0; counter < s.length(); counter++) {
            //Cast to int
            //Returns decimal ASCII value of character
            num = (int)s.charAt(counter);

            //Numbers are represented from 48 to 57 in ASCII ( 0 -> 9 )
            if (num > 47 && num < 58) {
                //ASCII goes to 48 in decimal before it is zero
                num -= 48;
            }

            storeNumbers[counter] = num;
            System.out.print(storeNumbers[counter]);
        }//for
    }
}
