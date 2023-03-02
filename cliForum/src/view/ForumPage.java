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
	String userInfo = null;
	boolean showAccess = true;

	public String init(String message, ProjectUtils pu, Scanner sc) {
		if (message.contains("id=")) {
			userInfo = pu.extractData(message)[0][1];
		}
		System.out.println(pu.getTitle("게시판", true));
		int select = -1;
		boolean run = true;

		while (run) {
			System.out.println(getPosts(pu, sc));
			if (userInfo != null) {
				if (showAccess)
					System.out.println(pu.getAccessInfo(userInfo));
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
				break;
			case 3:
				if (userInfo != null)
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
	String getPosts(ProjectUtils pu, Scanner sc) {
		String serverMessage;
		Controller ctl;
		serverMessage = (ctl = new Controller()).entrance("getPosts", pu, null);
		StringTokenizer st;
		List<PostBean> posts = new ArrayList<>();
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
			posts.add(p);
		}

		StringBuffer sb = new StringBuffer();
		sb.append("    번호      글쓴이                   제목\n");

		for (PostBean po : posts) {    
			sb.append(" [ " + po.getIndex() + " ]");
			sb.append(" [ " + po.getUser() + " ] ");
			sb.append((po.getTitle().length() < 15) ? po.getTitle() : po.getTitle().substring(0, 12) + "...");
			sb.append("\n");
		}
		return sb.toString();
		// sb.append(po.getContent());
	}

	void addPost(ProjectUtils pu, Scanner sc) {
		String clientData;
		String serverMessage;
		String[] names = { "번호", "작성자", "제목", "내용" };
		String[] data = new String[4];

		// 최신글 번호 불러오는 코드 추가
		Controller ctl;
		serverMessage = (ctl = new Controller()).entrance("getMaxPostIdx", pu, null);
		System.out.println(serverMessage);

		data[0] = serverMessage;

		data[1] = userInfo;
		data[2] = "";
		System.out.print("제목 : ");
		while (data[2].length() > 1)
		data[2] = sc.nextLine();

		System.out.println("내용 (1. 입력 종료): ");
		data[3] = pu.nextPara(sc);

		pu.confirm();
		boolean isExit = false;

		if (!(isExit = pu.confirmInput(isExit, sc))) {
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
