
public class Exception2 {
	
	//finally: finally is a block which always execute
	
	static void db() throws ArithmeticException
	{
		System.out.println("Connection Open");
		try
		{
			int s = 100/0;
			System.out.println("Query and Get the Result");
			System.out.println("Send Result to Helper");
		}
		finally
		{
			// resource clean up code
			System.out.println("Connection Close");
		}
	}
	
	static void helper() throws ArithmeticException
	{
		System.out.println("Call DB");
		db();
		System.out.println("Get Result from DB");
		System.out.println("Send Result to View");
	}
	
	static void view()
	{
		System.out.println("Call Helper");
		try
		{
			helper();
		}
		catch(ArithmeticException e)
		{
			System.out.println("Some Problem in Database");
			e.printStackTrace();
			return;
			
			//System.exit(0); // exit the program
		}
		System.out.println("Get Result from Helper");
		System.out.println("Print result to User");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		view();
		
		//System.out.println("Outside the Exception");

	}

}
