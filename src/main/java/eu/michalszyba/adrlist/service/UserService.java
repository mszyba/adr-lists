package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    void saveUser(User user);

    void updateUser(User user);

    void softDeleteUserById(Long id);

    User getUserById(Long id);
}
