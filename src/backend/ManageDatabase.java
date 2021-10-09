package backend;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import files.FileCommand;
import user.Daoist;

public class ManageDatabase {
	public static ArrayList<Daoist> database = new ArrayList<Daoist>();
	
	public static void print_database() { // just to help me debug
		for(int i=0; i<ManageDatabase.database.size(); i++) {
			database.get(i).print_DaoistInfo();
		}
	}
	
	public static void show_message(String x) { // auxiliary function
		System.out.println(x);
	}
	// returns index of daoist if found, else returns -1
	public static int search_user(String userName, String userId) {
		for(int i=0; i<database.size(); i++) {
			if(database.get(i).getName().equals(userName)) return i;
		}
		for(int i=0; i<database.size(); i++) {
			if(database.get(i).getUser_id().equals(userId)) return i;
		}
		show_message(userName+" not found!");
		return -1; // not found
	}
	
	// load databasea from harddrive text file
	public static void load_database(ArrayList<Daoist> db) {
		
		FileCommand file = new FileCommand();
		try {	
			//File file2 = new File(".");
			//for(String fileNames : file2.list()) System.out.println(fileNames);	
			
		File f = new File(DefaultValues.FILEPATH);
		file.read_from_file_to_database(f, db);
		} catch(FileNotFoundException e) {
			System.out.println("Failed to load database");
			e.printStackTrace();
		}
	}
	
	// save to harddrive
	public static void save_database(ArrayList<Daoist> db) {
		FileCommand file = new FileCommand();
		file.write_from_database_to_file(db, DefaultValues.FILEPATH);
	}
}
