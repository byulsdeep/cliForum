package controller;

import java.util.Scanner;
import java.util.StringTokenizer;

import model.Authentication;
import view.PageUtils;
import view.SignUpPage;

public class Controller {
	SignUpPage sup;
	String jobCode;
	String message = "잘못된 접근";
	public String entrance(String data, PageUtils pu, Scanner sc) {
		// jobCode?item=value&item2=value&item3=value
		if (data != null) {
			if (data.contains("?")) {
				jobCode = data.substring(0, data.indexOf("?"));
			} else {
				jobCode = data;
			}
			
			switch (jobCode) {
			case "moveSignUp":
				(new SignUpPage()).init(pu, sc);
				break;
			case "isIdUsed":
				message = (new Authentication()).backController(data);
				break;
			case "signUp":
				(new Authentication()).backController(data);
				break;
			}
		}
		return message;
	}
}
