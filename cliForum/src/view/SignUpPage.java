package view;

import java.util.Scanner;

import beans.User;

public class SignUpPage {

	public void init(PageUtils pu, Scanner sc) {
		System.out.println(pu.getTitle("회원가입"));
		signUp(pu, sc);
	}

	void signUp(PageUtils pu, Scanner sc) {
		String[] names = {
			"id", "pw", "birthday", "recoveryQ", "recoveryA"	
		};
		String[] data = new String[5];

		System.out.println("아이디: ");
		data[0] = sc.nextLine().trim();
		// 파일 쓰기 기능 완성하면 중복체크 기능 추가

		while (true) {
			System.out.println("비밀번호: ");
			data[1] = sc.nextLine().trim();
			System.out.println("비밀번호 확인: ");
			if (data[1].equals(sc.nextLine().trim())) {
				break;
			} else {
				System.out.println("비밀번호가 다릅니다.");
			}
		}

		while (true) {
			System.out.println("생년월일(19910101):  ");
			data[2] = sc.nextLine().trim();
			if (pu.isNum(data[2])) {
				if (data[2].length() == 8) {
					break;
				} else {
					System.out.println("19910101 형식으로 8자리 입력해주세요.");
				}
			} else {
				System.out.println("숫자를 입력해주세요.");
			}
		}

		String[] recoveryQ = { "내가 다닌 초등학교는?", "내 이모의 이름은?", "내가 가장 아끼던 장난감은?", "내가 가장 좋아하는 노래는?" };
		System.out.println("계정 복구용 질문을 선택해주세요: ");
		System.out.println(pu.getMenu(recoveryQ, false));
		int recoveryQIdx = -1;
		while (true) {
			try {
				recoveryQIdx = sc.nextInt();
				if (recoveryQIdx < 1 || recoveryQIdx > 4)
					continue;
				break;
			} catch (Exception e) {
				continue;
			} finally {
				pu.scannerClear(sc);
			}
		}
		data[3] = String.valueOf(recoveryQIdx);
		System.out.println(recoveryQ[recoveryQIdx - 1]);
		data[4] = sc.nextLine().trim();

		String[] options = {
			"확인", "취소"	
		};
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
		sb.append("정보를 확인해주세요.");
		System.out.println(sb);
		System.out.println(pu.getMenu(options, false));
		
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
				pu.scannerClear(sc);
			}
		}
		if (select == 1) {
			pu.makeTransferData("signUp", names, data);
		}
	}
}
