import java.util.List;

import javax.swing.table.AbstractTableModel;

public class UserTableModel extends AbstractTableModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1;
	private static final int EMP_NUM_COL = 0;
	private static final int USER_NAME_COL = 1;
	private static final int PASSWORD_COL = 2;
	private static final int IS_ADMIN_COL = 3;
	
	private String[] columnNames = { "Employee Number", "Username", "Password", "Admin" };
	private List<User> users;
	
	public UserTableModel(List<User> users)
	{
		this.users = users;
	}
	
	@Override
	public int getColumnCount() 
	{
		return columnNames.length;
	}
	
	@Override
	public int getRowCount() 
	{
		return users.size();
	}
	
	@Override
	public String getColumnName(int col)
	{
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) 
	{
		User tempUser = users.get(row);
		
		switch (col) {
		case EMP_NUM_COL:
			return tempUser.getEmpNumber();
		case USER_NAME_COL:
			return tempUser.getUserName();
		case PASSWORD_COL:
			return tempUser.getPassword();
		case IS_ADMIN_COL:
			return tempUser.isAdmin();
		default:
			return tempUser;
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int c)
	{
		return getValueAt(0, c).getClass();
	}
}
