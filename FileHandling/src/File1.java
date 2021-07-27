import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

class MyFilter implements FilenameFilter {

	@Override
	public boolean accept(File dirPath, String fileName) {
		// TODO Auto-generated method stub
		return fileName.endsWith(".mp3");
	}
}

public class File1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String path = "E:\\Java\\JavaJune2021_Eclipse\\File\\abc.txt";
		File file = new File(path);
		
		if(file.exists())
		{
			File file2 = new File("E:\\Java\\JavaJune2021_Eclipse\\File\\sample.txt");
			file.renameTo(file2);
			//file.delete();
			System.out.println("File Deleted");
		}
		else {
			file.createNewFile();
			System.out.println("File Created");
		}
		
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getPath());
		
		path = "E:\\Java\\JavaJune2021_Eclipse\\File\\abcd\\xyz\\tt\\ee\\gar";
		file = new File(path);
		file.mkdirs();
		//file.mkdir();
		
		file = new File("E:\\Java\\JavaJune2021_Eclipse\\File\\songs");
		MyFilter filter = new MyFilter();
		//File files[] = file.listFiles(filter);
		File files[] = file.listFiles((dirPath, fileName)->fileName.endsWith(".mp3"));
		File files2[] = file.listFiles((dirPath, fileName)->fileName.endsWith(".mp4"));
		File files3[] = file.listFiles((dirPath, fileName)->fileName.endsWith(".txt"));
		System.out.println("No of Files "+files.length);
		System.out.println("No of Files "+files2.length);
		System.out.println("No of Files "+files3.length);
		
		/*
		for(File f : files)
		{
			f.delete();
		}
		*/
		//System.out.println("No of Files "+files.length);
		
		
	}

}
