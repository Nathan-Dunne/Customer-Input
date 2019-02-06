/** Function: (First Year Semester 1 Java Project Part 2) Generate a username and password from data within customer.txt and store them in usernames.txt.
* 	Name: Nathan Dunne
* 	Date: 5/12/2015
*/

//Importing classes
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class PartTwo
{
	public static void main (String[] args) throws IOException
	{
		// Normal code for input/output paths
		//Scanner scan = new Scanner(System.in);
		//System.out.println(" Enter file to take data from here ");
		//inputFileName=scan.nextLine();
		//System.out.println(" Enter file to output data here ");
		//ouputFileName=scan.nextLine();

		//Declaring variables
		// I hardcoded the input/output paths becuase the project specified the file names.
		String inputFileName = "customer.txt";
		String outputFileName = "usernames.txt";
		String inputText;
		String userName;
		String usernamePart[]= new String [3];


		int divisors=0;
		int tempNumber=0;
		int i=0;
		int k=0;
		int extractedValue[]= new int [3];
		//I was surprised at how smooth the transition from a char to an ascii value was
		int asciiValue[]= new int[3];

		//I intentionally made this object overwrite to file instead of append.
		PrintWriter out = new PrintWriter(outputFileName);
		File inputFile = new File(inputFileName);
		Scanner in = new Scanner(inputFile);

		//Read the input from the file while input remains
		while (in.hasNext())
		{
			inputText = in.nextLine();
			System.out.println(inputText);
			i++;

			//Username Algorithm
			if (i==1)
			{	//Username part taken using the substring line
				usernamePart[0]=(inputText.substring(0,1)+ ".");
				//System.out.println(usernamePart[0]);
			}
			else if (i==2)
			{	//Second Part is simply their full second name
				usernamePart[1]=inputText;
				//System.out.println(usernamePart[1]);
			}
			else if (i==3)
			{
				usernamePart[2]=(inputText.substring(0,2));
				//System.out.println(usernamePart[2]);
			}

			// Password Algorithm
			if (i==1)
			{	//I took the length of their first name as added entropy
				extractedValue[0]=(inputText.length());
				//And the Ascii value of the intial of their first name in this case
				asciiValue[0] = inputText.charAt(0);
				//System.out.println(asciiValue[0]);
			}
			else if (i==2)
			{
				extractedValue[1]=(inputText.length());
				asciiValue[1] = inputText.charAt(1);
				//System.out.println(asciiValue[1]);
			}
			else if (i==3)
			{
				extractedValue[2]=(inputText.length());
				asciiValue[2] = inputText.charAt(1);
				//System.out.println(asciiValue[2]);
			}

			//Time to write to the file becuase we are now on the last line of the set of details
			else if (i==5)
			{
				int passwordPart[]= new int [3];
				//Multiplying both values together to make parts
				passwordPart[2]=asciiValue[0]*extractedValue[2];
				passwordPart[1]=asciiValue[1]*extractedValue[1];
				passwordPart[0]=asciiValue[2]*extractedValue[0];


				// Finding the divisors of my password parts and then making 3 prime numbers from them, a bit convuluted and unnessecary but I like the idea.
				while(k<passwordPart.length)
				{
					for(int j=1;j<=passwordPart[k];j++)
					{
						if(passwordPart[k]%j==0)
						{
							divisors++;
						}
						//Prime number making
						while(divisors>2)
						{
							//S.O.P "No not prime"
							//return check
							//boolean check=false;
							if(k==0) //Changing decrements and increments depending on order
							{
							passwordPart[k]=passwordPart[k]-1;
							}
							else if(k==1)
							{
								passwordPart[k]=passwordPart[k]+1;
							}
							else
							{
								passwordPart[k]=passwordPart[k]-1;
							}
						divisors=1;
						j=1;
						}
					}
				//System.out.println("There are " + divisors + " divisors of "+passwordPart[k]);
				k++;
				}
				//Creating the username from the manipulated strings
				userName=(usernamePart[0].concat(usernamePart[1])+(usernamePart[2]));
				System.out.println("\n" + "Username = " + userName);
				out.println(userName);

				//Multiplying three Prime numbers to create a Semiprime number to be used as a base for a password
				tempNumber=passwordPart[0]*passwordPart[1]*passwordPart[2];

				//Converting the Semiprime back to a String
				String tempString = String.valueOf(tempNumber);

				String finalPass=tempString.replace('2', 'E');
				finalPass=finalPass.replace('7', 'F');
				finalPass=finalPass.replace('4', 'G');

				//Making the password 6 characters max for user friendliness
					if(finalPass.length()>6)
					{
								finalPass=(finalPass.substring(0,6));
					}

				System.out.println("Password = " +finalPass + "\n" + "\n");
				out.println(finalPass);
				//Resetting counters
				k=0;
				i=0;


			}
		}
		in.close();
		out.close();
	}
}