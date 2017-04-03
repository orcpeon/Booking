import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		FileUtils fu = new FileUtils();
		UserInterface ui = new UserInterface();
		Controller contr = new Controller(ui, fu);
		ui.setRequestManager(contr);
		ui.printMainMenu();
	}

}
