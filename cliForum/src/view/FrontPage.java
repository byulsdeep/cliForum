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
		int select = -1;
		
		while (true) {
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
		(new Controller()).entrance("moveSignUp", pu, sc);
	}
	private void moveLogIn() {

	}
	private void moveForum() {
	}
}