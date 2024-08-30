import java.util.Scanner;

public class Hello {

	public static void main(String[] args) {
		System.out.println("Hello, World");
		Scanner s = new Scanner(System.in);
		String[] lname = new String[100];
		
		System.out.println("Enter Last Names. \".\" to end"); 
		String inName = "";
		int x =0;
		do
			{
				inName = s.next();
				lname[x] = inName;
				x++;
			
			} while (!inName.equals("."));
		System.out.println("Done");
		s.close();
		}
	}