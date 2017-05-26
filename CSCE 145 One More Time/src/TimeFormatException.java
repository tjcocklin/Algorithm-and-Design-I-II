
/**
 *  @author  Jeffrey Cocklin                           o_0
 *  @version  1.0, 26 March 2015                       0_o
 *  This creates the TimeFormatException class.        0_o
 *
 */


public class TimeFormatException extends Exception
{

    /**
     * Constructor 
     * @param String message
     */
        
    public TimeFormatException(String message)
    {
        super(message);

    }
    
    /**
     *Default Constructor 
     */
        
    public TimeFormatException()
    {
        super("Exception: This is not a form of time");

    }

}
