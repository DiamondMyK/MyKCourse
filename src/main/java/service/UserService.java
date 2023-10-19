package service;

import java.util.List;
import java.util.Map;

import model.User;

public interface UserService {

    public boolean insertUser(User user);

    public Boolean removeUser(String userid);

    public List<User> selectUsers(Map<String, Object> map);

    public boolean modifyUser(User user);

    public boolean banUser(String userid);

    public boolean checkUser(String username, String userpwd);

}
