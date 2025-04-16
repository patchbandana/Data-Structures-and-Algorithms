import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Foundry {
	
	public static void main(String[] args)
	{
		short xpos = -1;
		short ypos = -1;
		short playerNum = -1; //reference characters.csv file
		String name;
		String description;
		short offense;
		short defense;
		double value;
		
		ArrayList<String> charList = new ArrayList<>();
		
		boolean another = true;
		Scanner s = new Scanner(System.in);
		String filename = "../data/items.csv";
		String charFile = "../data/characters.csv";
		System.out.println("\nWelcome to the Foundry");

		init(charFile,charList);
		
		while(another)
		{
			//reset key values
			playerNum = -1;
			xpos = -1;
			ypos = -1;
			
			System.out.print("*******************\n\nEnter Item Name:>");
			name = s.nextLine();
			
				System.out.print("Is this item carried by a player? (y/n):>");
				String answer = s.nextLine();
				if(answer.toLowerCase().charAt(0) == 'y')
				{
					System.out.println("\n  Character List\n  ***********");

					for(int i = 0; i < charList.size(); i++)
					{
						System.out.println("  " + i + " - " + charList.get(i));
					}
					System.out.print("  ***********\n  Player number carrying "+ name + ":>");
					answer = s.nextLine();
					playerNum = Short.parseShort(answer);
				}
		
			System.out.print("\nEnter Item Description:>");
			description = s.nextLine();
		
			System.out.print("Enter Item Offensive Bonus:>");
			offense = Short.parseShort(s.nextLine());

			System.out.print("Enter Item Defensive Bonus:>");
			defense = Short.parseShort(s.nextLine());

			System.out.print("Enter Item Value:>");
			value = Double.parseDouble(s.nextLine());
			
			if(playerNum < 0)
			{
				System.out.print("Enter Starting X Position:>");
				xpos = Short.parseShort(s.nextLine());
	
				System.out.print("Enter Starting Y Position:>");
				ypos = Short.parseShort(s.nextLine());
			}

			writeToCSVFile(filename, name, description, offense, defense, value, xpos, ypos, playerNum);
			
			System.out.print("*******************\nAnother Item? (Y/N)");
			char choice = s.nextLine().charAt(0);
			
			if((choice == 'N') || (choice == 'n'))
			{
				another = false;
			}
		}
		System.out.println("GoodBye");
		s.close();
	}

	private static void writeToCSVFile(String filename, String name, String description, short offense, short defense, double value, short xpos, short ypos, short playerNum)
	{
		String outLine = "";
		try
		{
			FileWriter fw = new FileWriter(filename, true);
		
			if(playerNum >= 0)
			{
				outLine = String.format("%d,%d,%s,%s,%d,%d,%1.2f\n",-1,playerNum,name,description,offense,defense,value);
			}
			else
			{
				outLine = String.format("%d,%d,%s,%s,%d,%d,%1.2f\n",xpos,ypos,name,description,offense,defense,value);
				
			}
			
			fw.write(outLine);

			fw.close();
			
		}
		catch(IOException e)
		{
			System.out.println("File Error: " + filename);
		}
	}

	public static void init(String filename,ArrayList<String> charList)
	{ //Initialize Characters
		try
		{
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			String splitBy = ",";
						
			String[] data;
			
			charList.add("Main Char");
			
			while((line = br.readLine()) != null)
			{
				data = line.split(splitBy);
				
				short pType = Short.parseShort(data[11]);

				if(pType == 2)
				{
					charList.set(0, data[2]);
				}
				else
				{
					charList.add(data[2]);
				}
			}
			br.close();
		}
		catch(IOException e)
		{
			System.out.println("File Error: " + filename);
			e.printStackTrace();
		}
	}

	
}
