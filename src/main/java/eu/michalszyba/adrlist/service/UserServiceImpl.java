package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.User;
import eu.michalszyba.adrlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
//        User userById = getUserById(user.getId());
//        userById.setId(user.getId());
//        userById.setEmail(user.getEmail());
//        userById.setPassword(user.getPassword());
//        userById.setFirstName(user.getFirstName());
//        userById.setLastName(user.getLastName());
//        userById.setDescription(user.getDescription());
//        userById.setCompany(user.getCompany());
////        userById.setCreationDate();
////        userById.setModificationDate();

        this.userRepository.save(user);
    }

    @Override
    public void softDeleteUserById(Long id) {
        this.userRepository.softDelete(id);
    }

    @Override
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

    @Override
    public List<User> getAllUserByCompanyId(Long companyId) {
        return userRepository.findAllByCompanyId(companyId);
    }
}
