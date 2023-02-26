package view;

import java.util.Scanner;

public class SignUpPage {

	public void init(PageUtils pu, Scanner sc) {
		System.out.println(pu.getTitle("회원가입"));
		signUp(pu, sc);
	}

	void signUp(PageUtils pu, Scanner sc) {
		String id, pw, recoveryA, birthday;

		System.out.println("아이디: ");
		id = sc.nextLine().trim();
		// 파일 쓰기 기능 완성하면 중복체크 기능 추가

		while (true) {
			System.out.println("비밀번호: ");
			pw = sc.nextLine().trim();
			System.out.println("비밀번호 확인: ");
			if (pw.equals(sc.nextLine().trim())) {
				break;
			} else {
				System.out.println("비밀번호가 다릅니다.");
			}
		}

		while (true) {
			System.out.println("생년월일(19910101):  ");
			birthday = sc.nextLine().trim();
			if (pu.isNum(birthday)) {
				if (birthday.length() == 8) {
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
		int select = -1;
		while (true) {
			try {
				select = sc.nextInt();
				if (select < 1 || select > 4)
					continue;
				break;
			} catch (Exception e) {
				continue;
			} finally {
				pu.scannerClear(sc);
			}
		}
		System.out.println(recoveryQ[select - 1]);
		recoveryA = sc.nextLine().trim();

		String[] options = {
			"확인", "취소"	
		};
		StringBuffer sb = new StringBuffer();
		sb.append("아이디: " + id + "\n");
		sb.append("비밀번호: ");
		for (int i = 0; i < pw.length(); i++) {
			sb.append("*");
		}
		sb.append("\n");
		sb.append("생년월일: " + birthday + "\n");
		sb.append(recoveryQ[select - 1] + "\n");
		sb.append(recoveryA + "\n");
		sb.append("정보를 확인해주세요.");
		System.out.println(sb);
		System.out.println(pu.getMenu(options, false));
	}
}
