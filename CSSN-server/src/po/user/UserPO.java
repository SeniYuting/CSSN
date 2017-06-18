package po.user;

import java.io.Serializable;

import vo.user.Identity;

/**
 * A PO encapsulate the user information.
 * 
 * @author CaoYuting
 * 
 */
public class UserPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Identity identity;
	private String idNum;
	private String userName;
	private String password;
	private String institute;
	private String grade;

	// ������ʦ���ον�ʦ������Ա
	public UserPO(String idNum, String userName, String password,
			Identity identity) {
		this.idNum = idNum;
		this.userName = userName;
		this.password = password;
		this.identity = identity;
	}

	// Ժϵ������ʦ
	public UserPO(String idNum, String userName, String password,
			Identity identity, String institute) {
		this(idNum, userName, password, identity);
		this.institute = institute;
	}

	// ѧ��
	public UserPO(String idNum, String userName, String password,
			Identity identity, String institute, String grade) {
		this(idNum, userName, password, identity);
		this.institute = institute;
		this.grade = grade;
	}

	public String getIdNum() {
		return idNum;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public Identity getIdentity() {
		return identity;
	}

	public String getInstitute() {
		return institute;
	}

	public String getGrade() {
		return grade;
	}

	public String toString() {
		return idNum + " " + userName + " " + password + " "
				+ identity + " " + institute + " " + grade;
	}

}
