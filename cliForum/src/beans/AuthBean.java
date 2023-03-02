package beans;

public class AuthBean {
	private String id;
	private String pw;
	private String birthday;
	private String recoveryQ;
	private String recoveryA;
	
	public String toString() {
		return id + "," + pw + "," + birthday + "," + recoveryQ + "," + recoveryA;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getRecoveryQ() {
		return recoveryQ;
	}
	public void setRecoveryQ(String recoveryQ) {
		this.recoveryQ = recoveryQ;
	}
	public String getRecoveryA() {
		return recoveryA;
	}
	public void setRecoveryA(String recoveryA) {
		this.recoveryA = recoveryA;
	}
}
