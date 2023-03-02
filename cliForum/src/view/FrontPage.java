package view;

import java.util.Scanner;

import controller.Controller;
import interfaces.PageRules;
import utilities.ProjectUtils;

public class FrontPage implements PageRules {
	Scanner sc;
	ProjectUtils pu;
	String[] options = { "게시판  ", "로그인  ", "회원가입 " };
	String[] options2 = { "게시판  ", "로그아웃  ", "마이페이지" };
	String userInfo;
	public FrontPage() {
		init(null, new ProjectUtils(), new Scanner(System.in));
	}
	// 생성자에서 스캐너와 유틸을 생성하지 않은건 다른 페이지들과
	// 규정을 공유하기 위해 -> Interface PageRules
	public String init(String data, ProjectUtils pu, Scanner sc) {
		// userInfo == null, 여기서 안받음
		this.sc = sc;
		this.pu = pu;	
		int select = -1;
		// 리턴 없는데 컴파일 에러 안뜨네? 무한반복문 있어서 그런가
		while (true) {
			System.out.println(pu.getTitle());		
			if (this.userInfo != null) {
				System.out.println(pu.getMenu(options2, true));
			} else {
				System.out.println(pu.getMenu(options, true));
			}
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				continue;
			} finally {
				pu.scannerClear(sc);
			}
			switch (select) {
			case 1:
				moveForum();
				break;
			case 2:
				if (userInfo == null) {
					moveLogIn();	
				} else {
					moveLogOut();				
				}
				break;
			case 3:
				if (userInfo == null) {
					moveSignUp();
				} else {
					moveMyPage();					
				}
				break;
			case 0:
				System.out.println("종료");
				System.exit(0);
				break;
			}
		}
	}
	private void moveMyPage() {
		new Controller().entrance("moveMyPage?id=" + userInfo, pu, sc);
	}
	private void moveLogOut() {
		
	}
	private void moveSignUp() {
		new Controller().entrance("moveSignUp", pu, sc);
	}
	private void moveLogIn() {
		userInfo = new Controller().entrance("moveLogIn", pu, sc);
	}
	private void moveForum() {
		if (userInfo == null) {
			new Controller().entrance("moveForum", pu, sc);
		} else {
			new Controller().entrance("moveForum?id=" + userInfo, pu, sc);
		}
		
	}
}