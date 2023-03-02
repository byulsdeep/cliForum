package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import controller.Controller;
import interfaces.PageRules;
import utilities.ProjectUtils;

public class SignUpPage implements PageRules {

	public String init(String userInfo, ProjectUtils pu, Scanner sc) {
		// userInfo = null;
		System.out.println(pu.getTitle("회원가입", true));
		signUp(pu, sc);
		return null;
	}

	void signUp(ProjectUtils pu, Scanner sc) {
		String[] names = { "id", "pw", "birthday", "recoveryQ", "recoveryA" };
		String[] data = new String[5];
		String clientData;
		String serverMessage;
		boolean isExit = false;
		while (!isExit) {
			System.out.println("아이디: ");
			data[0] = sc.next();
			if (isExit = pu.exitCheck(data[0]))
				break;
			if (pu.containsComma(data[0])) 
				continue;
//			if (pu.isEmpty(data[0]))
//				continue;
			// 파일 쓰기 기능 완성하면 중복체크 기능 추가
			clientData = pu.makeTransferData("isIdUsed", names[0], data[0]);
			serverMessage = new Controller().entrance(clientData, null, null);
			if (serverMessage != null) {
				System.out.println("이미 사용중인 아이디입니다.");
			} else {
				System.out.println("사용 가능한 아이디입니다.");
				pu.confirm();
				break;
			}
		}
		
		isExit = pu.confirmInput(isExit, sc);
		
		while (!isExit) {
			System.out.println("비밀번호: ");
			data[1] = sc.next();
			if (isExit = pu.exitCheck(data[1]))
				break;
			if (pu.containsComma(data[1])) 
				continue;
			System.out.println("비밀번호 확인: ");
			String confirm = sc.next();
			if (isExit = pu.exitCheck(confirm))
				break;
			if (data[1].equals(confirm)) {
				break;
			} else {
				System.out.println("비밀번호가 다릅니다.");
			}
		}

		while (!isExit) {
			System.out.println("생년월일(19910101):  ");
			data[2] = sc.next();
			if (isExit = pu.exitCheck(data[2]))
				break;
			if (pu.containsComma(data[2])) 
				continue;
			if (!pu.isNum(data[2])) {
				System.out.println("숫자를 입력해주세요.");
				continue;
			}
			if (data[2].length() != 8) {
				System.out.println("19910101 형식으로 8자리 입력해주세요.");
				continue;
			}
			try {
				LocalDate.parse(data[2], DateTimeFormatter.ofPattern("yyyyMMdd"));
				break;
			} catch (Exception e) {
				System.out.println("정상적인 날짜를 입력해주세요.");
			}
		}
		String[] recoveryQ = { "내가 다닌 초등학교는?", "내 이모의 이름은?", "내가 가장 아끼던 장난감은?", "내가 가장 좋아하는 노래는?" };
		if (!isExit) {		
			System.out.println("계정 복구용 질문을 선택해주세요: ");
			System.out.println(pu.getMenu(recoveryQ, false));
		}
		int recoveryQIdx = -1;
		while (!isExit) {
			try {
				recoveryQIdx = sc.nextInt();
				if (isExit = pu.exitCheck(recoveryQIdx))
					break;
				if (recoveryQIdx < 1 || recoveryQIdx > 4)
					continue;
				break;
			} catch (Exception e) {
				continue;
			} finally {
				pu.scannerClear(sc);
			}
		}
		if (!isExit) {
			data[3] = String.valueOf(recoveryQIdx);
			System.out.println(recoveryQ[recoveryQIdx - 1]);			
		}
		
		while (!isExit) {
			data[4] = sc.next();	
			if (pu.containsComma(data[4])) 
				continue;
			break;
		}
		
		if (!(isExit = pu.exitCheck(data[4]))) {
			StringBuffer sb = new StringBuffer();
			sb.append("아이디: " + data[0] + "\n");
			sb.append("비밀번호: ");
			for (int i = 0; i < data[1].length(); i++) {
				sb.append("*");
			}
			sb.append("\n");
			sb.append("생년월일: " + data[2] + "\n");
			sb.append(recoveryQ[recoveryQIdx - 1] + "\n");
			sb.append(data[4] + "\n");
			sb.append("정보를 확인해주세요.\n");
			sb.append("전송 하기");
			System.out.println(sb);
			pu.confirm();
		}
		
		if (!(isExit = pu.confirmInput(isExit, sc))) {
			clientData = pu.makeTransferData("signUp", names, data);
			serverMessage = new Controller().entrance(clientData, pu, null);
			if (serverMessage.equals("true")) {
				System.out.println(pu.getTitle("가입 성공", false));
				System.out.println("환영합니다, " + data[0] + "님!");
				System.out.println("지금 로그인하시고 새 글을 작성해보세요!");
			}
		}

	}
}
