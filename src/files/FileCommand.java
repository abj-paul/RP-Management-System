package files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import user.Daoist;

public class FileCommand {
	public void write_from_database_to_file(ArrayList<Daoist> db, String filename) {
		try {
			String str = "";
			for(int i=0; i<db.size(); i++) {
				str=str+db.get(i).toString()+"\n";
		    }	
			//System.out.println("The file should save this : "+str);
			BufferedWriter out = new BufferedWriter(
	                   new FileWriter(filename, true));
	        out.write(str);
	        out.close();
		} catch (IOException file_error) {
		    System.out.println("An error occurred.");
		    file_error.printStackTrace();
		}
	    System.out.println("Successfully wrote to the file.");
	}
	
	public void read_from_file_to_database(File file, ArrayList<Daoist> db) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		while (scan.hasNext()) {
		    String line = scan.nextLine().toString();
		    Daoist user = new Daoist();
		    user.load_user(line);
		    db.add(user);
		}
	    System.out.println("Successfully loaded the file to database.");
	}
}
