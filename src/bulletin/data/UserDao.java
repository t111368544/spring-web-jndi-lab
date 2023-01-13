package bulletin.data;

import bulletin.User;
import bulletin.UserForm;

public interface UserDao {

	UserForm save(UserForm user);

	User save(User user);

	User findByUsername(String username);

}
