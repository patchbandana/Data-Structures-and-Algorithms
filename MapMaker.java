import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MapMaker 
{

	public static void main(String[] args) 
	{
		boolean another = true;
		Scanner s = new Scanner(System.in);
		String filename = "../data/map.csv";
		
		while(another)
		{
				
			System.out.print("\n\n*******************\nEnter Map Block Title:>");
			String title = s.nextLine();
		
			System.out.print("Enter Block Description:>");
			String description = s.nextLine();
		
			System.out.print("Enter Block X Position:>");
			int xpos = Integer.parseInt(s.nextLine());
		
			System.out.print("Enter Block Y Position:>");
			int ypos = Integer.parseInt(s.nextLine());
		
			
			int N = 0;
			int S = 0;
			int E = 0;
			int W = 0;
			
			System.out.print("From here, Can you move North?: (Y/N)>");
			char choice = s.nextLine().charAt(0);
			
			if((choice == 'y') || (choice == 'Y'))
			{
				N = 1;
			}

			System.out.print("From here, Can you move South?: (Y/N)>");
			choice = s.nextLine().charAt(0);
			
			if((choice == 'y') || (choice == 'Y'))
			{
				S = 1;
			}
			
			System.out.print("From here, Can you move East?: (Y/N)>");
			choice = s.nextLine().charAt(0);
			
			if((choice == 'y') || (choice == 'Y'))
			{
				E = 1;
			}
			
			System.out.print("From here, Can you move West?: (Y/N)>");
			choice = s.nextLine().charAt(0);
			
			if((choice == 'y') || (choice == 'Y'))
			{
				W = 1;
			}

			writeToCSVFile(filename, xpos, ypos, title, description, N, S, E, W);
			
			System.out.print("*******************\nAnother Block? (Y/N)");
			choice = s.nextLine().charAt(0);
			
			if((choice == 'N') || (choice == 'n'))
			{
				another = false;
			}
		}
		System.out.println("GoodBye");

	}

	private static void writeToCSVFile(String filename, int xpos, int ypos, String title, String description, int n,
			int s, int e, int w) 
	{
		String outLine = "";
		try
		{
			FileWriter fw = new FileWriter(filename, true);
		
			outLine = String.format("%d,%d,%s,%s,%d,%d,%d,%d\n",xpos,ypos,title,description,n,s,e,w);
			fw.write(outLine);

			fw.close();
			
		}
		catch(IOException ex)
		{
			System.out.println("File Error: " + filename);
		}		
	}

}
