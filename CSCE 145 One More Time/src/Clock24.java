/**
 *  @author  Jeffrey Cocklin                           o_0
 *  @version  1.0, 26 March 2015                       0_o
 *  This class converts 24-hour time to 12-hour time.  0_o
 *
 */

public class Clock24
{
    public static final int DEFAULT_HOURS= 0;
    public static final int DEFAULT_MINUTES= 0;
    public static final boolean DEFAULT_AM = true;
    
    private int hours, minutes;
    private boolean isAM;
    
    
    /**
     * setHours
     * 
     * Mutator that sets hours
     *
     * @param int hour, the hour to be set
     * 
     * @return boolean value representing whether the hour is a valid hour
     */
    
    private boolean setHours(int hour)
    {
       boolean invalidHour = false;
       int newHour= hour;
       
       if(newHour < 0|| newHour > 23)
           invalidHour= true;
       
       else    
           this.hours= newHour; 
       
       return invalidHour;
    }
    
    /**
     * setMinutes
     * 
     * Mutator that sets minutes
     *
     * @param int minutes, the minutes to be set
     * 
     * @return boolean value representing whether the minutes are valid minutes
     */
        
    private boolean setMinutes(int minutes) 
    {
        boolean invalidMin = false;
        int newMinutes= minutes;
        
        if(newMinutes < 0|| newMinutes > 59)
            invalidMin = true;
        
        else
            this.minutes= newMinutes;
        
        return invalidMin;
    }
    
    /**
     * setIsAm
     *
     * Mutator that sets the boolean value isAM
     * 
     * @param value, the boolean value to set
     *
     * @return void 
     */
        
    private void setIsAm(boolean value){this.isAM = value; }    
    
    /**
     * getHours
     * 
     * Mutator retrieves the int value hours
     * 
     * @return int this.hours
     */
        
    public int getHours(){return this.hours;}
    
    /**
     * getMinutes
     * 
     * Mutator that retrieves the in value minutes
     * 
     * @return int this.minutes
     */
        
    public int getMinutes(){return this.minutes;}
    
    /**
     * setTime
     * 
     * Method used to convert 24 hour time to 12 hour time 
     * 
     * @param int hours, the int value of hours in 24 hour time 
     * @param int minutes, the int value of minutes in 24 hour time
     * 
     * @throws TimeFormatException
     *
     * @return void
     */
           
    public void setTime(int hours, int minutes)throws TimeFormatException  
    {
        boolean hr = this.setHours(hours);
        boolean mn = this.setMinutes(minutes);
        
        
        if( hr && mn )
            throw new TimeFormatException("Exception: invalid hour & Minutes");
        
        else if(hr)
            throw new TimeFormatException("Exception: invalid hour");
        
        else if(mn) 
            throw new TimeFormatException("Exception: invalid minutes");
        
        else
        {
            if(hours <= 11)
        
                this.setIsAm(true);
            
            else   
                this.setIsAm(false);
    
            //time conversion   
            if(this.getHours() == 0 ||this.getHours()> 12)
                this.setHours(Math.abs(this.hours - 12));
                         
        }
    }
    
    /**
     * setTime
     * 
     * Method receives user input of military time and parses it into 
     * integer values if valid
     * 
     * @param String time, representing military time 
     * 
     * @throws TimeFormatException
     *
     * @return void
     */
    
    public void setTime(String time) throws TimeFormatException // throw and exception because we can
    {
        int hours= -1;
        int minute= -1;
        try
        {
            try
            { 
                String strHour = time.substring(0, time.indexOf(':'));
                String strMin =time.substring(time.indexOf(':')+1,time.length() );
                
                 hours= Integer.parseInt(strHour); 
                 minute= Integer.parseInt(strMin); 
                                           
            }
            catch(Exception e)
            {
                throw new TimeFormatException();
                          
            }
            setTime(hours, minute);    
        }    
        catch(TimeFormatException e)
        {
            throw e; 
        
        }
    }
    
    /**
     * printTime
     * 
     * Method displays the time in hours and minutes
     * 
     * @return void
     */
        
    public void printTime()
    {
        if(this.isAM == true)
        {
            if (this.getMinutes() == 0)
                System.out.println(this.hours+":"+this.minutes+"0AM");
            
            else if(this.getMinutes() > 0 && this.getMinutes() < 10)
                System.out.println(this.hours+":0"+this.minutes+"AM");
            
            else
                System.out.println(this.hours+":"+this.minutes+"AM");
                
        }
    
        else
        {
            if (this.getMinutes() == 0)
                System.out.println(this.hours+":"+this.minutes+"0PM");    
            
            else if(this.getMinutes() > 0 && this.getMinutes() < 10)
                System.out.println(this.hours+":0"+this.minutes+"PM");
                
            else    
                System.out.println(this.hours+":"+this.minutes+"PM");
                      
        }
            
    }
    
    /**
     * Constructor
     * @param hours
     * @param minutes
     */
            
    public Clock24(int hours, int minutes)
    {
        this.setHours(hours);
        this.setMinutes(minutes);
    }
    
    /**
     * Default Constructor
     **/
            
    public Clock24 ()
    {
        this.setHours(DEFAULT_HOURS);
        this.setMinutes(DEFAULT_MINUTES);
        this.setIsAm(DEFAULT_AM);
    }


}
