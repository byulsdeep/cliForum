package model;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class ServiceUtils {
	public String[][] extractData(String data) {
		//signUp?id=byulsdeep&pw=*****&birthday=19940602&recoveryQ=1&recoveryA=yanghwa
		StringTokenizer st = new StringTokenizer(data.substring(data.indexOf("?") + 1), "=&");
		String[][] exData = new String[st.countTokens() / 2][2];
		for (int i = 0; st.hasMoreTokens(); i++) {
			exData[i][0] = st.nextToken();
			exData[i][1] = st.nextToken();
		}
		return exData;
	}
}
