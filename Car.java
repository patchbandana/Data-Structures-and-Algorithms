
public class Car {
	public String vin;
	public String make;
	public String model;
	public short year;
	//public String color;
	//public double miles;
	//public double price;
	//public short speed;
	//public double direction;
	//public double gpsX;
	//public double gpsY;
	
	public void newCar(String[] data)
	{
		vin = data[0];
		make = data[1];
		model = data[2];
		year = Short.parseShort(data[3]);
		
		System.out.println("New Car Added = VIN " + vin);
		
		return;
	}
}
