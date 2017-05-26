/**
 *  @author  Jeffrey Cocklin                           o_0
 *  @version  1.0, 26 January 2015                     0_o
 *  This program converts a binary string  into a      o_0
 *  decimal value.                                     0_o
 */
import java.util.Scanner;

public class BinaryChanger 
{

		 
	public static void main(String[] args) 
	{
		String BinaryNumber;
		Scanner keyboard = new Scanner(System.in);
		
		int again, decimalNum, numInBit;   
		char characterNum;
		
		again = decimalNum = 0;
		boolean brokeIt;
		
						
		do
		{
			decimalNum = 0;
			brokeIt = false;
			
			System.out.println("Welcome to binary changer");
			System.out.println("Please input a 4 bit binary number: ");
			
			BinaryNumber= keyboard.nextLine();
			
			/**
			Ensures user inputs an appropriate 4 bit binary number
			**/
			
			while((BinaryNumber.length()!= 4))
			{
				System.out.println("Please use only 4 bit numbers, try again: ");
				BinaryNumber= keyboard.nextLine();
				
			}
		
			/**			
			Calculates the decimal value of user input if it is valid
			**/
			
			for(int i =0; i < 4; i++ )
			{
				
				characterNum =BinaryNumber.charAt(i); 
				numInBit = Character.getNumericValue(characterNum);
								
				if((numInBit < 0)||(numInBit)> 1 )
				{
					System.out.println("Binary numbers consist of only 1's and 0's");
				    
					i = 4;
				    brokeIt = true;
				}
				else
				{	
					if(i ==0)
					{
						if(numInBit == 1)
						{
							decimalNum += 8 ;
						} 
						
					}	
					else if(i== 1)
					{
						if(numInBit == 1)
						{
							decimalNum += 4;
						} 
						
					}
					else if(i == 2)
					{
						if(numInBit == 1)
						{
							decimalNum += 2;
						} 
						
					}
					else if(i== 3)	
					{
						if(numInBit == 1)
						{
							decimalNum += 1;
						} 
						
					}	
					
				}
			    
			}
			
			/**
			Continues of ends program based on user input.
			**/
			
			if(brokeIt == true )
			{	
				System.out.println("Would you like to continue Y = 1/N = 0: ");
				again = keyboard.nextInt();
			
				keyboard.nextLine();
			}
			
			else
			{
				System.out.println(BinaryNumber+" converted to decimal is: "+decimalNum);
				System.out.println("Would you like to continue Y = 1/N = 0: ");
		    
				again = keyboard.nextInt();
				keyboard.nextLine();
			
			}	
		
			/**
			Restricts user input to values 1 and 0 for continue/ending program.  
			**/
			
			while((again < 0)||(again > 1))
			{	
				System.out.println("Please use only 1 or 0: ");
				again = keyboard.nextInt();
				
				keyboard.nextLine();
			
			}	
		
		}while(again == 1);
		
		System.out.println("Goodbye.");
	
	}

}
