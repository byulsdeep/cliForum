package view;

import java.util.Scanner;

public class PageUtils {
	String makeTransferData() {
		return null;
	}
	String getTitle(String title) {
		StringBuffer sb = new StringBuffer();
		sb.append("==================================\r\n" + 
				"\r\n" + 
				"　　　                     "    + title + "\r\n" + 
				"\r\n" + 
				"==================================");

		return sb.toString();
	}
	String getTitle() {
		StringBuffer sb = new StringBuffer();

		sb.append("==================================\r\n" + "\r\n" + "　　　            CLI Forum v1.0\r\n" + "\r\n"
				+ "　　　　            designed by\r\n" + "\r\n" + "+-++-++-++-++-++-++-++-++-++-++-+\r\n"
				+ "|B||y||u||l||s||D||e||e||p||★||彡|\r\n" + "+-++-++-++-++-++-++-++-++-++-++-+\r\n"
				+ "==================================");
		return sb.toString();
	}
	String getMenu(String[] options) {
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < options.length; i++) {
			sb.append(i + 1 + ". " + options[i]);
			sb.append((i % 2 == 0) ? "\t" : "\n" );	
		}
		sb.append("0. 종료   ");
		return sb.toString();
	}
	void scannerClear(Scanner sc) {
		if (sc.hasNextLine())
			sc.nextLine();
	}
}