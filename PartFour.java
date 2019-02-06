/** Function: (First Year Semester 1 Java Project Part 4) Program to allow user to enter username and password to verify that the details are correct.
* 	Name: Nathan Dunne
* 	Date: 6/12/2015
*/

//I changed some of this code post-demo, mainly the selection structure of the password check to make it smoother.

//Importing classes
import java.io.File;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;


public class PartFour
{
	public static void main(String []args) throws FileNotFoundException
	{

	//Declaring Variables
	String inputFileName = "usernames.txt";
	String inputText;
	String usernameInput="";
	String passwordInput="";

	Boolean close=false;
	Boolean passwordFailed=false;
	Boolean quit=false;
	Boolean reset=false;
	Boolean begin=false;

	int i=0;
	int k=0;
	int p=0;
	int j=3;

	File inputFile = new File(inputFileName);
	Scanner inOne= new Scanner(inputFile);
	Scanner inTwo= new Scanner(inputFile);
	Scanner scan =new Scanner(System.in);

	//Counting loop to count the amount of lines in the file
	while(inOne.hasNext())
	{
		inputText=inOne.nextLine();
		i++;
	}
	//Assigning the i value to be the size of the arrays
	String username[]= new String [i+2];
	String password[]= new String [i+2];

	//Data storage loop
	while(inTwo.hasNext())
	{
		inputText=inTwo.nextLine();
		if(k%2==0)
		{
			username[k]=inputText;
			//These debug print outs can be used as an easy way to access the username and passwords in the command line
			System.out.println(" " +username[k]);
		}
		if(k%2!=0)
		{
			password[k]=inputText;
			System.out.println(" " +password[k] + "\n");
		}
		k++;

	}
	//Main loop
	//While the position of the username and password in their arrays is less than or equal to i
	//and close is not set to true and there is an attempt(j) left
	while(p<=i && close==false && j>0)
	{	//Intro
		if(begin==false)
		{
			System.out.println("\n Please enter your Username and Password. \n Username is case sensitive, password is not. You have " + j + " attempts to log in.");
			begin=true;
		}

		System.out.println("\n Please enter your Username:");
		usernameInput=scan.nextLine();
		//Loop to find if a username exists
		while (p!=i && quit==false)
		{	//Reset to be used if a user fails an attempt. The current position examined is set back to 0
			if(reset==true)
			{
				p=0;
				reset=false;
			}
			//If ANY username in the file matches the input, the position counter (p) is stopped
			if(usernameInput.equals(username[p])==true)
			{
				//System.out.println("Username Success");
				//System.out.println(p);
				quit=true;
			}
			else
			{	//If it doesn't exist p will run all the way through the array of usernames
				p++;
			}
				//The user is not told if it is an invalid username, I believe this is more secure than if they were
		}

		//System.out.println("position of" + p + "and amount of" + i);
		//quit reset so the password loop can be entered after failing with attempts remaining

		System.out.println(" Please enter your Password:");
		passwordInput=scan.nextLine();
		//The commented code below masks the input at the command line level but it doesn't show how many characters you typed in and I didn't like that. I also don't fully understand it so I didn't implement it.
		//Console consoleTwo = System.console();
		//char[] passwordTwo = consoleTwo.readPassword("Password: ");

		//The password associated with a username is stored afer that username so this selection compares the username to password at position+1 of the username *only*.
		if(passwordInput.equalsIgnoreCase(password[p+1])==true)
		{
			System.out.println("\n Username and Password Success.");
			System.out.println("\n You are now logged in. \n");
			close=true;
			quit=true;
		}
		else
		{	//Attempt Decrementation selection
			j--;
			if(j==0)
			{
				System.out.println(" \n Incompatible username and password.");
				System.out.println(" \n \n You have used your 3 attempts and failed to log in. You are now locked out of the system. Goodbye \n \n");
				close=true;
			}
			else if(j==1)
			{
				System.out.println(" \n Incompatible username and password. \n \n *WARNING!* You only have " +  (j) + " attempts left to log in. System lock out imminent. *WARNING!*");
				p=0;
				reset=true;
				quit=false;
			}
			else
			{
				System.out.println(" \n Incompatible username and password, you have " +  (j) + " attempts left to log in.");
				p=0;
				reset=true;
				quit=false;
			}
		}
	}

	inOne.close();
	inTwo.close();
	}
}