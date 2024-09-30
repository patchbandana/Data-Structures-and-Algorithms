import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WorldTemps {

	public static void main(String[] args) {
		//Linked List Controls
		
		//comma seperated value file, already provided
		String filename = "city_temperature.csv";
		
		//Creates new LinkedList object
		LinkedList ll = new LinkedList();
		
		//Feeds the file to the LinkedList class
		int count = initialize(filename, ll);
		
		int bad = ll.deleteValue(-99);
		
		double percent = (double)bad/count * 100;
		
		//Statement used to display how many invalid values were found and tossed
		System.out.println("Cleaning up " + bad + " values: " + percent + "%\n");
		
		//Finds the maximum temperature among all data
		Reading maxTemp = ll.findMax();
		//Finds the minimum temperature among all data after deleting.
		Reading minTemp = ll.findMin();
				
		//Print statements
		System.out.println("max temperature was: " + maxTemp.getAvgTemp() +
				" in " + maxTemp.getCity() + " on " + maxTemp.getMonth() + 
				"/" + maxTemp.getDay() + "/" + maxTemp.getYear());
		System.out.println("min temperature was: " + minTemp.getAvgTemp() +
				" in " + minTemp.getCity() + " on " + minTemp.getMonth() + 
				"/" + minTemp.getDay() + "/" + minTemp.getYear());
		
		//Finds average and displays 
		ll.worldAvgByYear();
		ll.usAvgByYear();
		//ll.print(); <-- prints every single temperature in the file
		
		//System.out.println("done");
		//System.out.println("Head at " + head.getCity());
	}

	public static int initialize(String filename, LinkedList ll)
	{
		String line = "";
		String splitBy = ",";
		int count = 0;
		try
		{
			//FileReader requests file from the operating system and returns the location/handle
			FileReader fr = new FileReader(filename);
			//BufferedReader allows you to operate with the data in the file
			BufferedReader br = new BufferedReader(fr);
			
			//Ignore first line of labels for data
			line = br.readLine(); 
			

			while (((line = br.readLine()) != null))
				{
					String[] data = line.split(splitBy);
					String region = data[0];
					String country = data[1];
					String state = data[2];
					String city = data[3];
					short month = Short.parseShort(data[4]);
					short day = Short.parseShort(data[5]);
					short year = Short.parseShort(data[6]);
					double avgtemp = Double.parseDouble(data[7]);
					
					//Creates a new reading of the file
					Reading r = new Reading(region, country, state, city, month, day, year, avgtemp);
					
					//Adds the reading to the Linked List, looping through until the end of the file
					ll.add(r);
					count++;
					
				}
			System.out.println(count + " records read");

			br.close();
		}
		catch(IOException e)
		{
			System.out.println("File Error: " + filename);
		}
		
		return count;
	}

}
