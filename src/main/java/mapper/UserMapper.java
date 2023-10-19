package mapper;

import java.util.List;
import java.util.Map;

import model.User;

public interface UserMapper {

    public int insertUser(User user);

    public int deleteUser(String userid);

    public List<User> selectUsers(Map<String, Object> map);

    public int modifyUser(User user);

    public int banUser(String userid);
}
