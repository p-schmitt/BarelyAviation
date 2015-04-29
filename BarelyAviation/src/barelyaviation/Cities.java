/* Program: BarelyAviation
 * This: cities.java
 * Date: Apr-13-2015
 * Author: P. Schmitt
 * Purpose: A class to instantiate an object representing a city.  The city acts
 *          as a destination for our airplane.
 */

package barelyaviation;
import java.util.Scanner;

//===========================class Cities======================
public class Cities 
{
    public int cityChosen = -1;
    private int economyPrice;
    private final int BEAV_RATE = 200;
    private final int RABB_RATE = 300;
    private final int TOAD_RATE = 400;
    private final int BUGG_RATE = 500;
    
    //=======================Cities========================
    public Cities()
    {
        displayCities();
        cityChosen = chooseCity();  
    }
    
    private void displayCities()
    {
        System.out.printf("What is this flight's destination? \n" 
                + "\t1 - Beaverdale, PA\n" 
                + "\t2 - Rabbit Shuffle, NC\n"
                + "\t3 - Toad Lick, AR\n"
                + "\t4 - Buggscuffle, TN\n"
                + "Choice: ");
    }
    
    //====================chooseCity======================
    private int chooseCity()
    {
        int num = -1;
        Scanner userIn = new Scanner(System.in);

        while(userIn.hasNext())
        {
           if( userIn.hasNextInt())
           {
                   num = userIn.nextInt();
                   if(num < 1 || num > 4)
                   {
                       System.out.println("Not a valid city choice!");
                   }
                   else
                   {  
                      return num;
                   }
           }
           else
           {
               System.out.println("Not a valid city choice!");
               userIn.next();
           }
        } 
        return num;
    }  
    
    //=============================calcFairs============================
    public int calcFairs()
    {
        
        if(cityChosen == 1)
        {
            this.economyPrice = BEAV_RATE;
        }
        else if(cityChosen ==2)
        {
            this.economyPrice = RABB_RATE;
        }
        
        else if (cityChosen == 3)
        {
            this.economyPrice = TOAD_RATE;
        }
        
        else 
        {
            this.economyPrice = BUGG_RATE;
        }
        
        return this.economyPrice;
    }
}