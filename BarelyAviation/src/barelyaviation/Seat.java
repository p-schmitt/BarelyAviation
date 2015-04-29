/* Program: BarelyAviation
 * This: seat.java
 * Date: Apr-14-2015
 * Author: P. Schmitt
 * Purpose: A class to instantiate an object representing a seat on an airplane.
 *          Each seat has a number, pass. name, section, and seat cost, and a 
 *          variable to represent its status as sold/unsold.  Contains methods to 
 *          display seat as a string and set a passenger name
 */
package barelyaviation;

//=======================class Seat=========================
public class Seat 
{
    public int seatNo;
    public String passName;
    public String section;
    public int seatCost;
    public boolean isSold;
    
    //==========================Seat=========================
    public Seat()//default constructor
    {
        
    }
    
    //=======================Seat==============================
    public Seat(int seatNo)//constructor using seat number
    {
        this.seatNo = seatNo;
    }
    
    //=====================seatToString======================
    public String seatToString()//returns a string representing information about a seat
    {
        String seatString;
        seatString = Integer.toString(seatNo) + "      $" + this.seatCost + "     " + this.section + "     " + passName;
        return seatString;
    }
    
    //===================setName================================
    public void setName(String name)//sets passenger name
    {
        this.passName = name;
    }
}
