import java.util.Scanner;

public class ObjArray {

	public static int maxcars = 20;
	public static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		int choice = 99;
		Car[] cars = new Car[maxcars];
		int currentCount = 0;

		//System.out.println("Choice = " + choice);

		while (choice > 0)
		{
			choice = menu();
			switch(choice)
			{
			case 0:
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

	public static void listCars(Car[] c, int count)
	{
		System.out.println("*************\n"
				+ "List of Cars: \n"
				+ "*************\n");
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
		
		return count;
	}

}

