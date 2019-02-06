/** Function: (First Year Semester 1 Java Project Part 3) Program to take in customer details and usernames+passwords, format and output to screen.
* 	Name: Nathan Dunne
* 	Date: 6/12/2015
*/

//I changed some of this code post-demo, added a selection structure for choosing what file you want to see.


//Importing classes
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class PartThree
{
	public static void main (String[] args) throws FileNotFoundException
	{

		//Declaring variables
		String inputFileName = "customer.txt";
		String inputFileNameTwo = "usernames.txt";
		String inputText="";
		String scannerInput="";
		String question[]= new String [5];
		//Hardcoded questions
		question[0]="First Name";
		question[1]="Second Name";
		question[2]="Date of Birth";
		question[3]="Email Address";
		question[4]="Mobile, Home or general Contact Number";
		String valueType[]= new String [2];
		//Hardcoded value types
		valueType[0]="Username";
		valueType[1]="Password";
		File inputFile = new File(inputFileName);
		File inputFileTwo = new File(inputFileNameTwo);
		Scanner inOne = new Scanner(inputFile);
		Scanner inTwo = new Scanner(inputFileTwo);
		Scanner scan = new Scanner(System.in);
		int i=0;
		boolean quit=false;
		boolean begin=false;
		boolean customersClose=false;
		boolean usernamesClose=false;

		//Main loop
		while(quit!=true)
		{	//Intro
			if(begin==false)
			{
				System.out.println(" \n What file would you like to look at? \n Please enter Usernames or Customers.");
				scannerInput=scan.nextLine();
				begin=true;
			}

			//Selection for customers
			if(scannerInput.equalsIgnoreCase("customers")==true && customersClose==false)
			{
				//System.out.println("DEBUG");
				while(customersClose==false )
				{
					System.out.println(" \n  Customer Details \n");
					System.out.println( "  _______________________");

					while (inOne.hasNext())
					{	//Looping through the lines, using the questions array for support
						inputText=inOne.nextLine();
						System.out.printf("\n     " +question[i] + ": " + inputText);
						i++;
						//End of the set of lines
						if(i==5)
						{
							System.out.println();
							System.out.println( "  _______________________");
							i=0;
						}
					}
					//I only want to show each file once, regardless of what they pick first
					customersClose=true;
					if(usernamesClose==false)
					{	//User options
						System.out.println(" \n Would you like to look at the Usernames file? \n Please enter Yes or No.");
						scannerInput=scan.nextLine();
						if(scannerInput.toLowerCase().contains("n")==true)
						{
							System.out.println(" \n This program will now close. Thank you \n");
							quit=true;
						}
						else
						{
						scannerInput="usernames";
						}
					}
				}
			}
			//Selection for usernames
			else if(scannerInput.equalsIgnoreCase("usernames")==true && usernamesClose==false)
			{
				//System.out.println("DEBUG");
				while(usernamesClose==false)
				{
					i=0;
					System.out.println();
					System.out.println(" \n \n  Usernames and Passwords \n");
					System.out.println( "  _______________________");

					while (inTwo.hasNext())
					{
						inputText = inTwo.nextLine();
						System.out.printf("\n     " + valueType[i] + ": " + inputText);
						i++;
						if(i==2)
						{
						System.out.println();
						System.out.println( "  _______________________");
						i=0;
						}
					}
					usernamesClose=true;
					if(customersClose==false)
					{
						System.out.println(" \n Would you like to look at the Customers file? \n Please enter Yes or No.");
						scannerInput=scan.nextLine();
						if(scannerInput.toLowerCase().contains("n")==true)
						{
							System.out.println(" \n This program will now close. Thank you \n");
							quit=true;
						}
						else
						{
						scannerInput="customers";
						}
					}
				}
			}
			//Exit options
			else if(scannerInput.equalsIgnoreCase("quit")==true || scannerInput.equalsIgnoreCase("exit")==true)
			{
				quit=true;
			}
			//When everything is shown at least once
			else if(customersClose==true && usernamesClose==true)
			{
				quit=true;
				System.out.println(" \n This program will now close. Thank you \n");
			}
			else
			{
				System.out.println(" \n Invalid selection. This program will now restart.");
				begin=false;
			}
		}
		inOne.close();
		inTwo.close();
	}
}
