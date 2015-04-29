/* Program: Barely Aviation
 * This: barelyaviation.java
 * Date: Apr-14-2015
 * Author: P. Schmitt
 * Purpose: A program to keep track of booking an airline that can have only one 
 *          destination/plane at a time.
 */

package barelyaviation;
import java.util.Scanner;
//======================class BarelyAviation======================
public class BarelyAviation 
{
    public static void main(String[] args) 
    {
        int menuChoice;
        Scanner myIn = new Scanner(System.in);
        Cities myDest = new Cities();
        Airplane myPlane = new Airplane(myDest);        
        do
        {
            Airplane.Menu();
            System.out.print("Choice: ");
            menuChoice = myIn.nextInt();
            if(menuChoice == 1)
            {
                myPlane.sellSeat();
                
                System.out.println();
            }
            else if (menuChoice == 2)
            {
                myPlane.refundSeat();
                System.out.println();
            }
            else if (menuChoice == 3)
            {
                System.out.println("Seat----Cost-----Class----Status");
                System.out.print(myPlane.printAvailableSeats());
                System.out.println();
            }
            else if (menuChoice == 4)
            {
                System.out.println("Seat----Cost-----Class----Name");
                System.out.print(myPlane.printSoldSeats());
                System.out.println();
            }
            else if ( menuChoice == 5)
            {
                myPlane.displayFinancials();
                System.out.println();
            }
            else if (menuChoice == 0)
            {
                System.out.println("Goodbye!");
            }
            else
            {
                System.out.println("Invalid selection!");
                System.out.println();
            }
        }while (menuChoice != 0);
    }
}