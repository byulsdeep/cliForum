package model;

import java.util.StringTokenizer;

import database.DataAccessObject;
import interfaces.ServiceRules;
import utilities.ProjectUtils;

public class Forum implements ServiceRules {

	public String backController(String data, ProjectUtils pu) {
		String message = "오류";
		String jobCode;
		if (data != null) {
			jobCode = pu.getJobCode(data);

			switch (jobCode) {
			case "deletePost":
				message = deletePost(data, pu);
				break;
			case "getPosts":
				message = getPosts(pu);
				break;
			case "addPost":
				message = addPost(data, pu);
				break;
			case "getMaxPostIdx":
				message = getMaxPostIdx(data, pu);
				break;
			}
		}
		return message;
	}
	public String deletePost(String data, ProjectUtils pu) {
		String message = null;
		String[][] exData = pu.extractData(data);
		String idx = exData[0][1];
		StringTokenizer st = new StringTokenizer(getPosts(pu), "\n");
		String token;
		StringBuilder sb = new StringBuilder();
		while (st.hasMoreTokens()) {
			if ((token = st.nextToken()).substring(0, token.indexOf("|")).equals(idx)) {
				sb.append("");
			} else {
				sb.append(token + "\n");
			}
		}	
		DataAccessObject dao = new DataAccessObject();
		if (dao.fileConnected(false, "/src/database/posts.txt", false)) {
			message = dao.insPost(sb.toString()) ? "true" : null;
		} else {
		}
		dao.fileClose(false);
		return message;
	}
	public String getPosts(ProjectUtils pu) {
		String message = null;
		DataAccessObject dao = new DataAccessObject();
		if (dao.fileConnected(true, "/src/database/posts.txt", false)) {
			message = dao.getPosts(pu);
		}
		dao.fileClose(true);
		return message;
	}
	public String getMaxPostIdx(String data, ProjectUtils pu) {
		String message = null;
		DataAccessObject dao = new DataAccessObject();
		if (dao.fileConnected(true, "/src/database/posts.txt", false)) {
			message = String.valueOf(dao.getMaxPostIdx(pu));
		}
		dao.fileClose(true);
		return message;
	}
	public String addPost(String data, ProjectUtils pu) {
		// addPost?글번호=1작성자=admin&제목=1&내용=1
		String message = null;
		String[][] exData = pu.extractData(data);
		DataAccessObject dao = new DataAccessObject();
		if (dao.fileConnected(false, "/src/database/posts.txt", true)) {
			message = dao.insPost(exData) ? "true" : null;
		}
		dao.fileClose(false);
		return message;
	}
}
