import java.io.File;
import java.io.IOException;

public class Exception1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Exception: Abnormal condition which terminates the program flow
		
		/*
		 * try{} -  try means guarded region
		 * **/
		
		System.out.println("DB Connection Open....");
		System.out.println("DB Query...");
		try {
			
			String path = "E:\\Java\\JavaJune2021_Eclipse\\File\\abc.txt";
			
			File file = new File(path);
			
			if(file.exists())
			{
				System.out.println("Yes this file is exist");
				file.delete();
			}
			else {
				file.createNewFile();
				System.out.println("File Created...");
			}
			
			/*
			String t = null;
			t.toLowerCase(); // throw new NullPointerException();
			
			int arr[] = new int[10];
			arr[11] = 100; // throw new ArrayIndexOutOfBoundsException();
			
			int e = 10 / 0; // throw new ArithmeticException();
			System.out.println("In Try-Block");
			
			*/
			
		}
		catch(ArithmeticException e)
		{
			System.out.println("You divide a number with zero "+e);
			return;
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("More than Array Bound(Size)");
			return;
		}
		catch(IOException e)
		{
			System.out.println("File can't be create");
		}
		
		/*catch(NullPointerException e)
		{
			System.out.println("Value is null");
			return;
		}*/
		/*
		catch(Exception e)
		{
			System.out.println("Some other problem arise, contact to system admin");
			return;
		}
		*/
		System.out.println("Get the result of Query");
		System.out.println("Print the result of the Query");
		System.out.println("Close the Connection");
		
	}

}
