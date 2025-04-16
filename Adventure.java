import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*	TO DO:
 * Validate map
 * Validate players
 * move players
 * combat
 * items on map
 * characters hold items
 * inventory
 * kill character
 * kill player
 * graphical map
 * steal
 * win
 */

public class Adventure 
{

	public static final int MAX_X = 10;
	public static final int MAX_Y = 10;

	public static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) 
	{
		int maxProfiles = 20;
		int numChars = 0;
		String charFile = "../data/characters.csv";
		Profile[] characters = new Profile[maxProfiles];
		//we are characters[0]
		
		String mapFile = "../data/map.csv";

		boolean playing = true;
		
		MapBlock[][] m = new MapBlock[MAX_X][MAX_Y];
		
		for( int i = 0; i < MAX_X; i++)
		{
			for(int j = 0; j < MAX_Y; j++)
			{
				m[i][j] = new MapBlock();
			}
		}
	
		init(mapFile, m);
		if(verify(m))
		{
			numChars = init(charFile,characters);
			
			while(playing)
			{
				showMap(m,characters);
				showChars(characters, numChars);
				playing = getMove(m,characters);
			}
		}
		System.out.println("Goodbye");	
		s.close();
	}
	
	private static void showChars(Profile[] c, int numChars) 
	{
		for(int idx = 1;idx < numChars;idx++)
		{
			if((c[0].xpos == c[idx].xpos) && (c[0].ypos == c[idx].ypos))
			{
				//same place
				System.out.println( c[idx].getName() + " is here");
			}
		}
		
	}
	
	public static void showMap(MapBlock[][] m, Profile[] characters)
	{
		System.out.printf("%s\n%s\n",m[characters[0].xpos][characters[0].ypos].getTitle(),m[characters[0].xpos][characters[0].ypos].getDescription());
		
	}

	public static boolean getMove(MapBlock[][] m, Profile[] characters)
	{
		boolean playing = true;
		
		System.out.println("Next Move: ");
		String command = s.nextLine();
		command = command.toLowerCase();
		char cmd = command.charAt(0);
		
		//n,s,e,w,x
		switch(cmd)
		{
		case 'n':
			if(m[characters[0].xpos][characters[0].ypos].go(direction.NORTH))
			{
				characters[0].ypos--;
			}
			else
			{
				System.out.println("Can't go that way...");
			}
			break;
		case 's':
			if(m[characters[0].xpos][characters[0].ypos].go(direction.SOUTH))
			{
				characters[0].ypos++;
			}
			else
			{
				System.out.println("Can't go that way...");
			}
			break;
		case 'e':
			if(m[characters[0].xpos][characters[0].ypos].go(direction.EAST))
			{
				characters[0].xpos++;
			}
			else
			{
				System.out.println("Can't go that way...");
			}
			break;
		case 'w':
			if(m[characters[0].xpos][characters[0].ypos].go(direction.WEST))
			{
				characters[0].xpos--;
			}
			else
			{
				System.out.println("Can't go that way...");
			}
			break;
		case 'x':
			playing = false;
			break;
		default:
			System.out.println("Not a valid command\n");
			break;
		}
		
		
		return playing;
	}
	
	public static int init(String filename,Profile[] c)
	{
		try
		{
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			String splitBy = ",";
			int pCount = 0;
			
			String[] data;
			
			
			
			while((line = br.readLine()) != null)
			{
				data = line.split(splitBy);
				c[pCount++] = new Profile(
						Short.parseShort(data[0]),
						Short.parseShort(data[1]),
						data[2],
						data[3],
						data[4],
						data[5],
						Short.parseShort(data[6]),
						Short.parseShort(data[7]),
						Short.parseShort(data[8]),
						Short.parseShort(data[9]),
						Short.parseShort(data[10]));
				
			}
			
			br.close();
			return pCount;
		}
		catch(IOException e)
		{
			System.out.println("File Error: " + filename);
			e.printStackTrace();
		}
		
		return -1;
	}

	
	public static void init(String filename, MapBlock[][] m)
	{
		try
		{
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			String splitBy = ",";
			
			String[] data;
			
			while((line = br.readLine()) != null)
			{
				data = line.split(splitBy);
				
				int xpos = Integer.parseInt(data[0]);
				int ypos = Integer.parseInt(data[1]);
				
				m[xpos][ypos] = new MapBlock(data[2],data[3],Integer.parseInt(data[4]),Integer.parseInt(data[5]),Integer.parseInt(data[6]),Integer.parseInt(data[7])); 
			}
			
			br.close();
		}
		catch(IOException e)
		{
			System.out.println("File Error: " + filename);
			e.printStackTrace();
		}
	}

	public static boolean verify(MapBlock[][] m)
	{ 
		boolean result = true;
		
		for(int x = 0; x < MAX_X; x++)
		{
			for(int y = 0; y < MAX_Y; y++)
			{
				if(m[x][y].go(direction.NORTH))
				{
					if(y == 0)
					{
						System.out.println("map block [" + x + "][" + y + "] - NORTH from " + m[x][y].getTitle() + " does not exist");
						result = false;
					} 
					else if(m[x][y-1].getTitle().equalsIgnoreCase("VOID"))
					{
						System.out.println("map block [" + x + "][" + y + "] - NORTH from " + m[x][y].getTitle() + " does not exist");
						result = false;
					}
				}
				if(m[x][y].go(direction.SOUTH))
				{
					if(y == (MAX_Y - 1))
					{
						System.out.println("map block [" + x + "][" + y + "] - NORTH from " + m[x][y].getTitle() + " does not exist");
						result = false;
					} 
					else if(m[x][y+1].getTitle().equalsIgnoreCase("VOID"))
					{
						System.out.println("map block [" + x + "][" + y + "] - SOUTH from " + m[x][y].getTitle() + " does not exist");
						result = false;
					}
				}
				if(m[x][y].go(direction.EAST))
				{
					if(x == (MAX_X - 1))
					{
						System.out.println("map block [" + x + "][" + y + "] - NORTH from " + m[x][y].getTitle() + " does not exist");
						result = false;
					} 
					else if(m[x+1][y].getTitle().equalsIgnoreCase("VOID"))
					{
						System.out.println("map block [" + x + "][" + y + "] - EAST from " + m[x][y].getTitle() + " does not exist");
						result = false;
					}
				}
				if(m[x][y].go(direction.WEST))
				{
					if(x == 0)
					{
						System.out.println("map block [" + x + "][" + y + "] - NORTH from " + m[x][y].getTitle() + " does not exist");
						result = false;
					} 
					else if(m[x-1][y].getTitle().equalsIgnoreCase("VOID"))
					{
						System.out.println("map block [" + x + "][" + y + "] - WEST from " + m[x][y].getTitle() + " does not exist");
						result = false;
					}
				}
			}

		}
		return result;
	}
	
}
