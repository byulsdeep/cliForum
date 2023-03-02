package view;

import java.util.Scanner;

import controller.Controller;
import interfaces.PageRules;
import utilities.ProjectUtils;

public class LogInPage implements PageRules {
	String userInfo = null;
	public String init(String userInfo, ProjectUtils pu, Scanner sc) {
		// userInfo = null
		System.out.println(pu.getTitle("로그인", true));
		userInfo = logIn(pu, sc);
		return userInfo;
	}
	
	String logIn(ProjectUtils pu, Scanner sc) {
		String[] names = { "id", "pw" };
		String[] data = new String[2];
		String clientData;
		String serverMessage = null;
		boolean isExit = false;
		
		while (!isExit) {
			System.out.println("아이디 : ");
			data[0] = sc.next();
			if (isExit = pu.exitCheck(data[0]))
				break;
			System.out.println("비밀번호: ");
			data[1] = sc.next();
			pu.confirm();
			
			if (!(isExit = pu.confirmInput(isExit, sc))) {
				clientData = pu.makeTransferData("logIn", names, data);
				serverMessage = new Controller().entrance(clientData, pu, null);
				if (serverMessage != null) {
					System.out.println(pu.getTitle());
					System.out.println(pu.getAccessInfo(serverMessage));
					break;
				} else {
					System.out.println("인증 정보가 잘못되었습니다.");
				}
			}
		}
		return serverMessage;
	}
}
