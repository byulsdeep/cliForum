package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import beans.PostBean;
import controller.Controller;
import interfaces.PageRules;
import utilities.ProjectUtils;

public class ForumPage implements PageRules {
	String[] options = { "글쓰기  ", "마이페이지  ", "홈페이지 " };
	String[] options2 = { "홈페이지 " };
	String[] options3 = { "글삭제    ", "돌아가기 " };
	String[] options4 = { "돌아가기 " };
	String userInfo = null;
	boolean showAccess = true;
	List<PostBean> posts;

	public String init(String message, ProjectUtils pu, Scanner sc) {
		if (message.contains("id=")) {
			userInfo = pu.extractData(message)[0][1];
		}
		System.out.println(pu.getTitle("게시판", false));
		int select = -1;
		boolean run = true;

		while (run) {
			System.out.println(getPosts(pu, sc));
			if (userInfo != null) {
				System.out.println(pu.getMenu(options, true));
			} else {
				System.out.println(pu.getMenu(options2, true));
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
				if (userInfo != null) {
					addPost(pu, sc);
				} else {
					run = false;
				}
				break;
			case 2:
				moveMyPage(pu, sc);
				break;
			case 3:
				if (userInfo != null)
					run = false;
				break;
			case 0:
				System.out.println("종료");
				System.exit(0);
				break;
			default:
				showPostDetail(select, pu, sc);
			}
		}
		return null;
	}
	private void moveMyPage(ProjectUtils pu, Scanner sc) {
		new Controller().entrance("moveMyPage?id=" + userInfo, pu, sc);
	}
	void showPostDetail(int idx, ProjectUtils pu, Scanner sc) {
		PostBean po = posts.get(idx - 4 );
		StringBuilder sb = new StringBuilder();
		sb.append("==================================\n");
		sb.append(po.getTitle() + "\n");
		sb.append(po.getUser() + " | " + po.getDate() + " " + po.getTime() + "\n");
		sb.append("----------------------------------\n");
		sb.append(po.getContent());
		sb.append("==================================");
		System.out.println(sb.toString());	
		int select;
		boolean run = true;
		while (run) {
			sb.setLength(0);
			sb.append(pu.getMenu((userInfo != null) ? options3 : options4, true));
			sb.append("\n==================================");
			System.out.println(sb);
			try {
				select = sc.nextInt();
			} catch (Exception e) {
				continue;
			} finally {
				pu.scannerClear(sc);
			}
			switch (select) {
			case 1:
				if (userInfo != null) {
					deletePost(po.getIndex(), pu, sc);
				} else {
					run = false;					
				}
				break;
			case 2:
				if (userInfo != null) run = false;
				break;
			case 0:
				System.out.println("종료");
				System.exit(0);
				break;
			}
		}
	}
	void deletePost(int idx, ProjectUtils pu, Scanner sc) {
		String serverMessage = null;
		Controller ctl;
		System.out.println("정말 삭제하시겠습니까?");
		pu.confirm();
		boolean isExit = false;
		if (!(isExit = pu.confirmInput(isExit, sc))) {
			serverMessage = (ctl = new Controller()).entrance("deletePost?postIdx=" + idx, pu, null);
		}
		System.out.println(serverMessage != null ? "게시글이 삭제되었습니다." : "오류");
	}
	String getPosts(ProjectUtils pu, Scanner sc) {
		String serverMessage;
		Controller ctl;
		serverMessage = (ctl = new Controller()).entrance("getPosts", pu, null);
		StringTokenizer st;
		posts = new ArrayList<>();
		PostBean p;
		String content;
		st = new StringTokenizer(serverMessage, "|\n");
		while (st.hasMoreTokens()) {
			p = new PostBean();
			p.setIndex(Integer.parseInt(st.nextToken()));
			p.setUser(st.nextToken());
			p.setTitle(st.nextToken());
			content = st.nextToken();
			p.setContent(content.replace("<newLine>", "\n"));
			p.setDate(st.nextToken());
			p.setTime(st.nextToken());
			posts.add(p);
		}

		StringBuffer sb = new StringBuffer();
		//0. 1. 번은 홈페이지, 종료 기능이므로 2번 부터 게시글 선택
		//2. 아름다운 구속		                    		댓글수
		//틀니 | 13:15/2023.03.02 | 조회 99 | 추천 99
		for (int i = 0; i < posts.size(); i++) {   
			sb.append(i + 4 + ". ");
			sb.append(posts.get(i).getTitle() + "\n");
			sb.append(posts.get(i).getUser() + "\t");
			sb.append((pu.getDate().equals(posts.get(i).getDate()) ? posts.get(i).getTime() : posts.get(i).getDate().substring(5,10)));
			//sb.append("조회 "); 조회수, 추천수, 댓글수는 여유되면 추가
			sb.append((i < posts.size() - 1) ? "\n----------------------------------\n" : "\n==================================");
		}
		return sb.toString();
	}

	void addPost(ProjectUtils pu, Scanner sc) {
		String clientData;
		String serverMessage;
		String[] names = { "번호", "작성자", "제목", "내용", "작성날짜", "작성시간" };
		String[] data = new String[6];

		// 최신글 번호 불러오는 코드 추가
		Controller ctl;
		serverMessage = (ctl = new Controller()).entrance("getMaxPostIdx", pu, null);
		data[0] = serverMessage;
		
		data[1] = userInfo;
		data[2] = "";
		System.out.print("제목 : ");
		data[2] = sc.nextLine();
		System.out.println("내용 (1. 입력 종료): ");
		data[3] = pu.nextPara(sc);
		
		pu.confirm();
		boolean isExit = false;

		if (!(isExit = pu.confirmInput(isExit, sc))) {
			data[4] = pu.getDate();
			data[5] = pu.getTime();
			clientData = pu.makeTransferData("addPost", names, data);
			serverMessage = ctl.entrance(clientData, pu, null);
			if (serverMessage.equals("true")) {
				System.out.println("글이 정상적으로 등록되었습니다.");
				System.out.println("==================================");
				showAccess = false;
			}
		}
	}
}
