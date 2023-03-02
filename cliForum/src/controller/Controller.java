package controller;

import java.util.Scanner;

import model.Authentication;
import model.Forum;
import utilities.ProjectUtils;
import view.ForumPage;
import view.LogInPage;
import view.SignUpPage;

public class Controller {
	SignUpPage sup;
	String jobCode;
	String message = "잘못된 접근";
	public String entrance(String data, ProjectUtils pu, Scanner sc) {
		// jobCode?item=value&item2=value&item3=value
		if (data != null) {
			jobCode = pu.getJobCode(data);
			System.out.println("controller data : " + data);
			switch (jobCode) {
			
			/* frontend*/
			case "moveSignUp":
				new SignUpPage().init(null, pu, sc);
				break;
			case "moveLogIn":
				message = new LogInPage().init(null, pu, sc);
				break;
			case "moveForum":
				System.out.println(data);
				new ForumPage().init(data, pu, sc);
				break;
			/* backend */
			case "isIdUsed":
			case "signUp":
			case "logIn":		
				message = new Authentication().backController(data, pu);		
				break;
			case "getPosts":
			case "addPost":
			case "getMaxPostIdx":
				message = new Forum().backController(data, pu);
				break;
			}
		}
		//System.out.println("contollerMessage: " + message);
		return message;
	}
}
