package view;

import java.util.Scanner;

import controller.Controller;

public class FrontPage {
	Scanner sc;
	PageUtils pu;
	String[] options = { "게시판  ", "로그인  ", "회원가입 " };
	public FrontPage() {
		sc = new Scanner(System.in);
		pu = new PageUtils();
		init();
		sc.close();
	}
	private void init() {
		System.out.println(pu.getTitle());
		System.out.println(pu.getMenu(options));
		while (true) {
			switch (sc.nextInt()) {
			case 1:
				System.out.println("게시판");
				break;
			case 2:
				System.out.println("로그인");
				break;
			case 3:
				moveSignUp();
				break;
			case 0:
				System.out.println("종료");
				System.exit(0);
				break;
			}
		}
	}
	private void moveSignUp() {
		Controller ctl = new Controller();
		ctl.entrance("moveSignUp", pu);
	}
	private void moveLogIn() {

	}
	private void moveForum() {
	}
}