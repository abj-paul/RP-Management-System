package launcher;

import java.awt.EventQueue;
import java.util.ArrayList;

import backend.ManageDatabase;
import frontend.AdminInterface;
import user.Daoist;

public class Main {
	public static void main(String[] args) {
		ManageDatabase.load_database(ManageDatabase.database);
		AdminInterface.run_AdminInterface(args);
	}
}
