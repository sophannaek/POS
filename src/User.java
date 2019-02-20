public class User 
{
	private int empNumber;
	private String userName;
	private String password;
	private boolean isAdmin;
	
	public User(int empNumber, String userName, String password, boolean isAdmin)
	{
		this.empNumber = empNumber;
		this.userName = userName;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	public User(User aUser)
	{
		this.empNumber = aUser.empNumber;
		this.userName = aUser.userName;
		this.password = aUser.password;
		this.isAdmin = aUser.isAdmin;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public int getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(int empNumber) {
		this.empNumber = empNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public boolean equals(Object user)
	{
		if (user == this)
			return true;
		if (!(user instanceof User))
			return false;
		User myUser = (User) user;
		if (empNumber == myUser.empNumber || userName.equals(myUser.userName))
			return true;
		else
			return false;
	}
	
	@Override
	public String toString()
	{
		return String.format("Employee Number = %d, User Name = %s, Password = %s", empNumber, userName, password);
	}
}