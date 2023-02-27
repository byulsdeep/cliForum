package model;

import database.DataAccessObject;
import interfaces.ModelRules;

public class Authentication implements ModelRules {
	
	public String backController(String data) {
		String message = "오류";
		String jobCode;
		if (data != null) {
			jobCode = data.substring(0, data.indexOf("?"));
			
			switch (jobCode) {
			case "isIdUsed":
				message = isIdUsed(data);
				break;
			case "signUp":
				message = signUp(data);
				break;
			}
		}
		return message;
	}
	private String isIdUsed(String data) {
		String message = "false";
		String[][] exData = (new ServiceUtils()).extractData(data);
		DataAccessObject dao = new DataAccessObject();
		String[] userIds;
		if (dao.fileConnected(true, "/src/database/users.txt", false)) {
			userIds = dao.getUsersId().split(",");
			for (String id : userIds) {
				if (exData[0][1].equals(id)) {
					message = "true";
					break;
				}
			}
		}
		return message;
	}
	
	private String signUp(String data) {
		String message = "";
		String[][] exData = (new ServiceUtils()).extractData(data);
		DataAccessObject dao = new DataAccessObject();
		if (dao.fileConnected(false, "/src/database/users.txt", true)) {
			
		}
		return null;
	}
}
