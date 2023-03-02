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
			case "getPosts":
				message = getPosts(data, pu);
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
	public String getPosts(String data, ProjectUtils pu) {
		System.out.println("Forum/getPosts");
		String message = null;
		String[][] posts;
		StringTokenizer st;
		DataAccessObject dao = new DataAccessObject();
		if (dao.fileConnected(true, "/src/database/posts.txt", false)) {
			System.out.println("fileConnected");
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
		//addPost?글번호=1작성자=admin&제목=1&내용=1
		String message = null;
		String[][] exData = pu.extractData(data);
		DataAccessObject dao = new DataAccessObject();
		if (dao.fileConnected(false, "/src/database/posts.txt", true)) {
			message = dao.insPost(exData) ? "true" : null;
		}
		dao.fileClose(false);
		System.out.println(data);
		return message;
	}
}
