package beans;

public class User {
	private String id;
	private String pw;
	private String birthday;
	private int recoveryQ;
	private String recoveryA;
	
	public User(String id, String pw, String birthday, int recoveryQ, String recoveryA) {
		this.id = id;
		this.pw = pw;
		this.birthday = birthday;
		this.recoveryQ = recoveryQ;
		this.recoveryA = recoveryA;
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
	public int getRecoveryQ() {
		return recoveryQ;
	}
	public void setRecoveryQ(int recoveryQ) {
		this.recoveryQ = recoveryQ;
	}
	public String getRecoveryA() {
		return recoveryA;
	}
	public void setRecoveryA(String recoveryA) {
		this.recoveryA = recoveryA;
	}
}
