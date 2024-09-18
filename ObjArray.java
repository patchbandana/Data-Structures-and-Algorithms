import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*To DO:
 * list
 * save to file
 * read from file
 * delete
 * filename "cars.csv"
 */

public class ObjArray {

	public static int maxcars = 20;
	public static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {


		Car[] cars = new Car[maxcars];
		String filename = "cars.csv";

		int currentCount = 0;
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);

			currentCount = load(br, cars);
			int choice = 99;

			while (choice > 0)
			{
				choice = menu();
				switch(choice)
				{
				case 0:
					save(filename, cars, currentCount);
					break;
				case 1:
					listCars(cars, currentCount);
					break;
				case 2:
					currentCount = addCar(cars, currentCount);
					break;
				case 3:
					currentCount = removeCar(cars, currentCount);
					break;
				default:
					break;
				}
			}

		}
		catch (IOException e)
		{
			System.out.println("File error: " + filename);
		}
		System.out.println("Goodbye");

		s.close();

	}
	public static int menu()
	{
		int choice = -1;

		while ((choice < 0 || choice > 3))
		{
			System.out.print("Car Showroom\n"
					+ "***********\n"
					+ "1. List Cars\n"
					+ "2. Add Car\n"
					+ "3. Remove Car\n"
					+ "0. Exit\n"
					+ "***********\n");

			choice = s.nextInt();
		}

		return choice;
	}

	public static void save(String outFile, Car[] cars, int carCount)
	{
		int outCount = 0;
		try
		{
			System.out.println("\nExporting... ");
			FileWriter writeToFile = new FileWriter(outFile, false);
			for (int c = 0; c < carCount; c++)
			{
				System.out.println("Exporting " + c);
				String outString = String.format("%s,%s,%s,%d\n", cars[c].vin, cars[c].make, cars[c].model, cars[c].year);
				writeToFile.write(outString);
				outCount++;
			}
			System.out.println("Wrote " + outCount + " Cars\nDone.\n");
			writeToFile.close();
		}
		catch (IOException e)
		{
			System.out.println("File" + outFile + " ERROR\n");
			e.printStackTrace();
		}
	}

	public static int load(BufferedReader br, Car[] c)
	{
		int count = 0;
		System.out.println("*************\n"
				+ "Loading Cars: \n"
				+ "*************\n");

		String line = null;
		String splitBy = ",";

		try
		{
			while ((((line = br.readLine()) != null)) && (count < maxcars))
			{
				String[] data = line.split(splitBy);
				c[count] = new Car();
				c[count].newCar(data);

				count++;
			}

			br.close();
		}

		catch (IOException e)
		{
			System.out.println("File Read Error");
		}

		c[count] = new Car();

		return count;
	}

	public static void listCars(Car[] c, int count)
	{
		System.out.println("*************\n"
				+ "List of Cars: \n"
				+ "*************\n");

		for (int x = 0; x < count; x++)
		{
			System.out.printf("%d  %s\t%s\t%s\t%d\n", x + 1, c[x].vin, c[x].make,
					c[x].model, c[x].year);
		}
	}

	public static int addCar(Car[] c, int count)
	{
		System.out.println("*************\n"
				+ "Add Cars: \n"
				+ "*************\n");
		c[count] = new Car();

		String dump = s.nextLine();

		System.out.print("VIN: ");
		c[count].vin = s.nextLine();
		System.out.print("Make: ");
		c[count].make = s.nextLine();
		System.out.print("Model: ");
		c[count].model = s.nextLine();
		System.out.print("Year: ");
		c[count].year = s.nextShort();


		count++;

		return count;
	}

	public static int removeCar(Car[] c, int count)
	{
		System.out.println("*************\n"
				+ "Remove Cars: \n"
				+ "*************\n");

		if (count < 1) 
			System.out.println("No cars to delete, returning to menu...");
		else 
		{
			listCars(c, count);
			System.out.print("Delete which car ID#: ");

			int choice = s.nextInt();

			while (choice > count)
			{
				System.out.print("Error: Car not found. Please try again: ");
				choice = s.nextInt();
			}

			c[choice - 1] = c[count - 1]; 
			count--;

		}
		return count;
	}
}
