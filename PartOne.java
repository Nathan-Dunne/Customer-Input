/** Function: (First Year Semester 1 Java Project Part 1) Program to take in customer details.
* 	Name: Nathan Dunne
* 	Date: 4/12/2015
*/

//Importing classes
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class PartOne
{
	public static void main (String[] args) throws IOException
	{
		PrintWriter out = new PrintWriter(new FileWriter("customer.txt",true));//boolean "append" = true
		Scanner scan = new Scanner(System.in);

		//Declaring variables
		String outputFileName = "customer.txt";
		String input;
		String terminate="";
		//I hardcoded these questions for them to remain within the project specification.
		String question[]= new String [5];
		question[0]="First Name";
		question[1]="Second Name";
		question[2]="Date of Birth";
		question[3]="Email Address";
		question[4]="Mobile, Home or general Contact Number";
		String answer[]= new String [5];

		boolean close=false;
		boolean diffPathOne=false;
		boolean diffPathTwo=false;
		boolean begin=false;

		int i=0;
		int k=0;
		int p=0;

		//Main loop in which everything will take place, i<6 where therse are 5 lines of data I wish to take in
		while (i<6 && close==false)
		{
			//Introduction, will only play once per run
			if(begin==false)
			{
				System.out.println("\n Hello World. Customer details database entry loaded. \n \n Please follow the instructions on screen. \n Entry cannot be an empty space or less than 3 characters long. \n Your second name cannot be longer than 20 characters for the \n purpose of your username.");
				System.out.println(" When entering details you may type quit or exit at any time to leave. \n \n Thank you for using this program. \n");
				System.out.println(" __________________________________________________________ ");
				begin=true;
			}
			//Taking in data from the keyboard, my questions for the user are hardcoded.
			System.out.println("\n  Please enter your " + question[i] + ":");
			input= scan.nextLine();
			//Exit options for the user
			if((input.equalsIgnoreCase("quit")==true) || (input.equalsIgnoreCase("exit")==true))
			{
				close=true;
				System.out.println("\n This program will now close. Thank you \n");
			}
			//Error checking, mainly to allow part two to work properly. Input length >20 @ line 2 check is to make sure usernames are not longer than 20 lines.
			else if((input.equals("")==true) || (input.length()<3) || (i==1 && input.length()>20))
			{
				System.out.println(" Invalid entry.  Would you like to re-enter your " + question[i] + "? \n");
				System.out.println(" Please enter Yes or No.");
				terminate=scan.nextLine();
					//User options. Restart or close.
					if(terminate.toLowerCase().contains("n")==true)
					{
						System.out.println("\nWould you like this program to restart? \n");
						System.out.println("Please enter Yes or No.");
						terminate=scan.nextLine();
						if(terminate.toLowerCase().contains("n")==true)
						{
							System.out.println("\nNo data has been written to file. \n");
							System.out.println("This program will now close. Thank you \n");
							close=true;
						}
						else
						{
							System.out.println("\n The program has restarted. \n");
							System.out.println("\n No data has been written to file. \n");
							i=0;
						}
					}
			}
			//Storing the answer in an Array for ease later on.
			else
			{
				answer[i]=input;
				//System.out.println(answer[i]);
				i++;
			}

			//If a full set of 5 lines of data has been taken in
			if(i==5)
			{	//User options
				System.out.println("\n \n These are the details you have entered:  \n");
				while (k<5)
				{	//Using an array to loop through the questions and answers
					System.out.println("\t" + question[k]+ ": " + answer[k]);
					k++;
				}
				k=0;
				System.out.println("\n Are these details correct? Please enter Yes or No.");
				terminate=scan.nextLine();
				if(terminate.toLowerCase().contains("n")==true)
				{
					i=0;
					System.out.println("\n No data has been written to file. \n");
					diffPathOne=true;
				}
				else
				{	//Trimming down spaces to go neatly into part two
					while (p<5)
					{
						out.println(answer[p].trim());
						p++;
					}
					System.out.println(" This data has been written to the file " + outputFileName + "\n");
					diffPathTwo=true;
				}
				//User option to restart the program to continue entering sets of details
				System.out.println(" Do you wish to continue entering sets of details? ");
				System.out.println(" Please enter Yes or No. \n");
				terminate=scan.nextLine();
				if(terminate.toLowerCase().contains("n")==true)
				{
					close=true;
					if ((diffPathTwo==false) && (diffPathOne=false))
					{
					System.out.println(" This data has been written to the file " + outputFileName + "\n");
					}

					System.out.println("\n This program will now close. Thank you \n");
				}
				else
				{
					i=0;
					System.out.println();
				}
			}
		}
		out.close();
	}
}