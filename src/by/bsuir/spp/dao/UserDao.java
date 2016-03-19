package by.bsuir.spp.dao;

import by.bsuir.spp.bean.User;

import java.util.List;

public interface UserDao extends GenericDao<User, Integer> {

    List<User> getAllUsers();

    List<Integer> getUserIds();

    User checkUser(User user);

    boolean checkLogin(String login);
}
