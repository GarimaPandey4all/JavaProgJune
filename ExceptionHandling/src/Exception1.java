
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
			int e = 10 / 0; // infinity
		}
		catch(ArithmeticException e)
		{
			System.out.println("You divide a number with zero "+e);
			return;
		}
		System.out.println("Get the result of Query");
		System.out.println("Print the result of the Query");
		System.out.println("Close the Connection");
		
	}

}
