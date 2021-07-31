import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class WriteDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		System.out.println("Enter the data to write in a file");
		Scanner scanner = new Scanner(System.in);
		
		String txt = scanner.nextLine();
		
		FileOutputStream fo = new FileOutputStream("E:\\Java\\JavaJune2021_Eclipse\\FileHandling\\src\\test.txt");
		
		fo.write(txt.getBytes());
		
		fo.close();
		
		System.out.println("Data Write in a file");

	}

}
