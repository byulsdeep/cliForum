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
	String makeTransferData(String jobCode, String name, String data) {
		StringBuffer sb = new StringBuffer();
		sb.append(jobCode + "?");
		sb.append(name + "=" + data);
		return sb.toString();
	}
	String makeTransferData(String jobCode, String[] names, String[] data) {
		StringBuffer sb = new StringBuffer();
		sb.append(jobCode + "?");
		for (int i = 0; i < names.length; i++) {
			sb.append(names[i] + "=");
			sb.append((i < names.length - 1) ? data[i] + "&" : data[i]); 
		}
		return sb.toString();
	}
	boolean isNum(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	boolean isEmpty(String s) {
		return s.length() == 0 ? true : false;
	}
	
	int confirm(Scanner sc) {
		String[] options = { "확인", "취소" };
		System.out.println(getMenu(options, false));

		int select = -1;
		while (true) {
			try {
				select = sc.nextInt();
				if (select < 1 || select > 2)
					continue;
				break;
			} catch (Exception e) {
				continue;
			} finally {
				scannerClear(sc);
			}
		}
		return select;
	}
}
