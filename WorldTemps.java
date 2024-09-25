import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WorldTemps {

	public static void main(String[] args) {
		//Linked List Controls

		String filename = "city_temperature.csv";
		
		LinkedList ll = new LinkedList();
		
		initialize(filename, ll);
		
		Reading maxTemp = ll.findMax();
		Reading minTemp = ll.findMin();
				
		System.out.println("max temperature was: " + maxTemp.getAvgTemp() +
				" in " + maxTemp.getCity() + " on " + maxTemp.getMonth() + 
				"/" + maxTemp.getDay() + "/" + maxTemp.getYear());
		System.out.println("min temperature was: " + minTemp.getAvgTemp() +
				" in " + minTemp.getCity() + " on " + minTemp.getMonth() + 
				"/" + minTemp.getDay() + "/" + minTemp.getYear());
		
		ll.worldAvgByYear();
		ll.usAvgByYear();
		//ll.print();
		
		//System.out.println("done");
		//System.out.println("Head at " + head.getCity());
	}

	public static void initialize(String filename, LinkedList ll)
	{
		String line = "";
		String splitBy = ",";
		try
		{
			//FileReader requests file from the operating system and returns the location/handle
			FileReader fr = new FileReader(filename);
			//BufferedReader allows you to operate with the data in the file
			BufferedReader br = new BufferedReader(fr);
			
			//Ignore first line of labels for data
			line = br.readLine(); 
			int count = 0;

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
	}

}
