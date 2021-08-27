package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.User;
import eu.michalszyba.adrlist.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public void updateUser(User user) {
        Long userId = user.getId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String password = user.getPassword();
        String description = user.getDescription();

        this.userRepository.updateUser(firstName, lastName, password, description, userId);
    }

    public void softDeleteUserById(Long id) {
        this.userRepository.softDelete(id);
    }

    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword("");
            return user;
        } else {
            throw new EntityNotFoundException();
        }
    }

    public List<User> getAllUserByCompanyId(Long companyId) {
        return userRepository.findAllByCompanyId(companyId);
    }
}