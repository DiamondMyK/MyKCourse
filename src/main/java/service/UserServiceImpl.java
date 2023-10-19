package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.UserDao;
import model.User;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDao();

    @Override
    public boolean insertUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public Boolean removeUser(String userid) {
        return userDao.deleteUser(userid);
    }

    @Override
    public List<User> selectUsers(Map<String, Object> map) {
        return userDao.selectUsers(map);
    }

    @Override
    public boolean modifyUser(User user) {
        return userDao.modifyUser(user);
    }

    @Override
    public boolean banUser(String userid) {
        return userDao.banUser(userid);
    }

    @Override
    public boolean checkUser(String username, String userpwd) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        map.put("userpwd", userpwd);
        List<User> userList = userDao.selectUsers(map);
        if (userList.size() != 0) {
            return true;
        }
        return false;
    }

}
