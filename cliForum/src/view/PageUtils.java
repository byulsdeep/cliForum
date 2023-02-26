package view;

import java.util.Scanner;

public class PageUtils {
	String getMenu(String[] options, boolean exitButton) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < options.length; i++) {
			sb.append(i + 1 + ". " + options[i]);
			sb.append((i % 2 == 0 && options[i].length() < 10) ? "\t" : "\n");
		}
		sb.append((exitButton) ? "0. 종료   " : "");
		return sb.toString();
	}
	String getTitle(String title) {
		StringBuffer sb = new StringBuffer();
		sb.append("==================================\r\n" + "\r\n" + "　　　                     " + title + "\r\n"
				+ "                            0. 취소\r\n" + "==================================");

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
	void scannerClear(Scanner sc) {
		if (sc.hasNextLine())
			sc.nextLine();
	}
	String makeTransferData() {
		return null;
	}
	boolean isNum(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
