package controller;

import java.util.StringTokenizer;

import view.PageUtils;
import view.SignUpPage;

public class Controller {
	StringTokenizer st;
	SignUpPage sup;

	String message = "잘못된 접근";
	public String entrance(String data, PageUtils pu) {
		// jobCode?item=value&item2=value&item3=value
		if (data != null) {
			st = new StringTokenizer(data, "?=&");
			String[] tokens = new String[st.countTokens()];
			for (int i = 0; st.hasMoreTokens(); i++) {
				tokens[i] = st.nextToken();
			}
			switch (tokens[0]) {
			case "moveSignUp":
				sup = new SignUpPage();
				sup.init(pu);
				return null;
			}
		}
		return message;
	}
}
