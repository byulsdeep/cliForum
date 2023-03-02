package view;

import java.util.Scanner;

import interfaces.PageRules;
import utilities.ProjectUtils;

public class MyPage implements PageRules {
	String[] options = { "홈페이지 " };
	String userInfo = null;
	
	public String init(String message, ProjectUtils pu, Scanner sc) {
		if (message.contains("id=")) {
			userInfo = pu.extractData(message)[0][1];
		}
		System.out.println(pu.getTitle("마이페이지", false));
		int select = -1;
		boolean run = true;
		while (run) {
			System.out.println("내 아이디: " + userInfo);
			System.out.println("==================================");
			System.out.println(pu.getMenu(options, true));
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				continue;
			} finally {
				pu.scannerClear(sc);
			}
			switch (select) {
			case 1:
				run = false;
				break;
			case 0:
				System.out.println("종료");
				System.exit(0);
				break;
			}
		}		
		return null;
	}
}
