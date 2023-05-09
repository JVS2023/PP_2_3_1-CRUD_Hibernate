package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getListUsers();//read
    void addUser(User user); //create
    void removeUser(Long id);//delete
    User getUserById(long id);//read
    void changeUser(User user);//update
}
