package model;

import database.DataAccessObject;
import interfaces.ServiceRules;
import utilities.ProjectUtils;

public class Authentication implements ServiceRules {
	
	public String backController(String data, ProjectUtils pu) {
		String message = "오류";
		String jobCode;
		if (data != null) {
			jobCode = pu.getJobCode(data);
			
			switch (jobCode) {
			case "isIdUsed":
				message = isIdUsed(data, pu);
				break;
			case "signUp":
				message = signUp(data, pu);
				break;
			case "logIn":
				message = logIn(data, pu);
			}
		}
		System.out.println("loginMessage" + message);
		return message;
	}
	private String logIn(String data, ProjectUtils pu) {
		String message = null;
		System.out.println(pu);
		System.out.println(data);
		String[][] exData = pu.extractData(data);
		DataAccessObject dao = new DataAccessObject();
		if (dao.fileConnected(true, "/src/database/users.txt", false)) {
			message = dao.getUserInfo(exData);
		}
		dao.fileClose(false);
		return message;
	}
	private String isIdUsed(String data, ProjectUtils pu) {
		String message = null;
		String[][] exData = pu.extractData(data);
		DataAccessObject dao = new DataAccessObject();
		String[] userIds;
		if (dao.fileConnected(true, "/src/database/users.txt", false)) {
			userIds = dao.getIdList().split(",");
			for (String id : userIds) {
				if (exData[0][1].equals(id)) {
					message = "true";
					break;
				}
			}
		}
		dao.fileClose(true);
		return message;
	}
	
	private String signUp(String data, ProjectUtils pu) {
		//signUp?id=byulsdeep&pw=****&birthday=19940602&recoveryQ=1&recoveryA=yanghwa
		String message = null;
		String[][] exData = pu.extractData(data);
		//AuthBean ab = new AuthBean(exData[0][1], exData[1][1], exData[2][1], exData[3][1], exData[4][1]);
		DataAccessObject dao = new DataAccessObject();
		if (dao.fileConnected(false, "/src/database/users.txt", true)) {
			message = dao.insUser(exData) ? "true" : null;
		}
		dao.fileClose(false);
		return message;
	}
}
