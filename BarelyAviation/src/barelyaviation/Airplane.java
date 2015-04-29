/* Program: BarelyAviation
 * This: airplane.java
 * Date: Apr-14-2015
 * Author: P. Schmitt
 * Purpose: A class to instantiate an object representing an airplane with methods
 *          to sell/refund seats and display financial information, such as profits
 *          and losses with a by-class breakdown.
 */
package barelyaviation;
import java.util.Scanner;

//==================class Airplane=================
public class Airplane 
{
    private Seat[] seats = new Seat[20];
    
    
    private final int ECON_FACTOR = 1;
    private final int BUS_FACTOR = 2;
    private final int FIRST_FACTOR = 4;
    
    private final int TOTAL_ECON = 8;
    private final int TOTAL_BUS = 6;
    private final int TOTAL_FIRST = 4;
    private final int MAX_SEATS = TOTAL_ECON + TOTAL_BUS + TOTAL_FIRST;
    
    private int flightCost ;
    private int flightSubtotal=0;
    
    private int seatsSold;
    private int seatsAvailable;
    
    private int econSold=0;
    private int busSold=0;
    private int firstSold=0;
    
    private int econSubtotal=0;
    private int busSubtotal=0;
    private int firstSubtotal=0;
    
    //=======================Airplane=============================   
    public Airplane (Cities myDest) //constructor assigns seat numbers, sets seat prices, calculates flight cost.
    {
        flightCost = myDest.calcFairs() * MAX_SEATS;
        
        for (int index = 1; index <= MAX_SEATS; index++)
        {   
            seats[index]  = new Seat(index);
            seats[index].seatCost = myDest.calcFairs();
            seats[index].isSold = false;
            seats[index].passName = "Unsold";
            
            if(seats[index].seatNo <= TOTAL_FIRST)
                {
                    seats[index].seatCost = seats[index].seatCost * FIRST_FACTOR;
                    seats[index].section = "First";
                }
        
            else if (seats[index].seatNo <= (TOTAL_FIRST+TOTAL_BUS))
                {
                    seats[index].seatCost = seats[index].seatCost * BUS_FACTOR;
                    seats[index].section = "Business";
                }
        
            else 
                {
                    seats[index].seatCost = seats[index].seatCost * ECON_FACTOR;
                    seats[index].section = "Economy";
                }
        }
        seats[0] = null;
        seats[19] = null;
    }
    
    //====================printAvailableSeats=============================
    public String printAvailableSeats()//check isSold status and prints unsold seats
    {
        String seatsString = "";
        for(int index = 1; index < MAX_SEATS + 1; index++)
        {
            if(seats[index].isSold == false)
            {
            seatsString+= (seats[index].seatToString() + "\n") ;
            }
        }
        return seatsString;
    }
    
    //====================printSoldSeats=============================
    public String printSoldSeats()//check isSold status and prints sold seats
    {
        String seatsString = "";
        for(int index = 1; index < MAX_SEATS + 1; index++)
        {
            if(seats[index].isSold == true)
            {
            seatsString+= (this.seats[index].seatToString() + "\n") ;
            }
        }
        return seatsString;
    }
    
    //===================chooseSeat===============================
    public int chooseSeat()//returns an int representing a user choice for a specific seat no.
    {
        int num = -1;
        Scanner userIn = new Scanner(System.in);

        while(userIn.hasNext())
        {
           if( userIn.hasNextInt())
           {
                   num = userIn.nextInt();
                   if(num < 1 || num > 18)
                   {
                       System.out.println("Not a valid seat!");
                   }
                   else
                   {  
                      return num;
                   }
           }
           else
           {
               System.out.println("Not a valid seat!");
               userIn.next();
           }
        } 
        return num;
    }
    
    //============================sellSeat==================================
    public void sellSeat()//set isSold=true, prompt for name, increment class count, adds cost to class subtotal
    {
        if(seatsSold == MAX_SEATS)
        {
            System.out.println("The flight is full!");
        }
        else
        {
            System.out.println(printAvailableSeats());
            Scanner myIn = new Scanner (System.in);
            System.out.print("Enter the seat you would like to sell: ");
            int seatChoice = chooseSeat();
            while(seats[seatChoice].isSold == true)
            {
                System.out.println("Choose another seat.  This seat has already been sold!");
                seatChoice = chooseSeat();
            }
            System.out.print("Enter passenger name: ");
            String passName = myIn.nextLine();
            seats[seatChoice].passName = passName;
            seatsSold++;
            seats[seatChoice].isSold = true;
            if(seats[seatChoice].seatNo <=4)
            {
                firstSold++;
                firstSubtotal += seats[seatChoice].seatCost; 
            }
            else if(seats[seatChoice].seatNo <=10)
            {
                busSold++;
                busSubtotal += seats[seatChoice].seatCost;
            }
            else
            {
                econSold++;
                econSubtotal += seats[seatChoice].seatCost;
            }
        }     
    }
    
    //=====================refundSeat=============================
    public void refundSeat()//refund seat, modifies subtotals, seat sold count
    {
        if(seatsSold == 0)
        {
            System.out.println("You haven't sold any seats yet!");
        }
        else
        {
            System.out.println(printSoldSeats());
            System.out.print("Enter the seat you would like to refund: ");
            int seatChoice = chooseSeat();
            while(seats[seatChoice].isSold == false)
            {
                System.out.println("Choose another seat.  This seat has not been sold!");
                seatChoice = chooseSeat();
            }
            seats[seatChoice].isSold = false;
            if(seats[seatChoice].seatNo <=4)
            {
                firstSold--;
                firstSubtotal -= seats[seatChoice].seatCost; 
            }
            else if(seats[seatChoice].seatNo <=10)
            {
                busSold--;
                busSubtotal -= seats[seatChoice].seatCost;
            }
            else
            {
                econSold--;
                econSubtotal -= seats[seatChoice].seatCost;
            }
            seatsSold--;
        }
    }
    
    //===========================displayFinancials=========================
    public void displayFinancials()//calculates and displays financial information
    {
        flightSubtotal = firstSubtotal + busSubtotal + econSubtotal;
        seatsAvailable = MAX_SEATS - seatsSold;
        int firstLeft = TOTAL_FIRST - firstSold;
        int busLeft = TOTAL_BUS - busSold;
        int econLeft = TOTAL_ECON - econSold;
        int profitLoss = flightSubtotal - flightCost;
        System.out.println("Seats Available: " + seatsAvailable);
        System.out.println("---------------------------");
        System.out.println("Total Seats Sold: " + seatsSold);
        System.out.println("First Class Sold: " + firstSold + " (" + firstLeft + " available)");
        System.out.println("Bus. Class Sold: " + busSold + " (" + busLeft + " available)");
        System.out.println("Econ. Class Sold: " + econSold + " (" + econLeft + " available)");
        System.out.println("---------------------------");
        System.out.println("Current Ticket Sales: $" + flightSubtotal);
        System.out.println("First Class Sales Total: $" + firstSubtotal);
        System.out.println("Bus. Class Sales Total: $" + busSubtotal);
        System.out.println("Econ. Class Sales Total: $" + econSubtotal);
        System.out.println("---------------------------");
        System.out.println("Flight Cost: " + flightCost);
        System.out.println("Net Profit/Loss: " + profitLoss );
        if(profitLoss >= 0)
        {
            System.out.println("We're making money!");
        }
        else
        {
            System.out.println("We're losing money!  We need to sell more tickets!");
        }
        
    }
    
    //========================Menu============================
    public static void Menu()//prints main menu for user input
    {
        System.out.println("1. Sell a seat");
        System.out.println("2. Refund a seat");
        System.out.println("3. Display unsold seats");
        System.out.println("4. Display sold seats");
        System.out.println("5. Display financial information");
        System.out.println("---------------------------------");
        System.out.println("0. Quit");
        System.out.println();
    }
}
