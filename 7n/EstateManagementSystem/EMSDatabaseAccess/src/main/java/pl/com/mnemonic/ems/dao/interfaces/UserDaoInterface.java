package pl.com.mnemonic.ems.dao.interfaces;

import java.util.List;

import pl.com.mnemonic.ems.entity.User;



public interface UserDaoInterface {

	public void adduser(User user);
	public List<User> getUsers();
	public User getUser(int usUserIdPk);
	public void deleteUser(int usUserIdPk);
	public void saveOrUpdate(User user);
	public boolean deleteUserNotify(int usUserIdPk);
	public User findByMail(String mail);
	public List<User> getUserAccountsByPlaceUserId(int idPlaceuser);
}
