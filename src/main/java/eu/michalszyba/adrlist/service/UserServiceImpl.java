package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.User;
import eu.michalszyba.adrlist.model.UserRole;
import eu.michalszyba.adrlist.repository.UserRepository;
import eu.michalszyba.adrlist.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private final UserRepository userRepository;
    private final UserRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    public void addWithDefaultRole(User user) {
        UserRole defaultRole = roleRepository.findByRole(DEFAULT_ROLE);
        user.getRoles().add(defaultRole);
        String passwordHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        userRepository.save(user);
    }

//    public void saveUser(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setActive(true);
//        UserRole userRole = roleRepository.findByRole("ROLE_USER");
//        user.setRoles(new HashSet<UserRole>(Arrays.asList(userRole)));
//        userRepository.save(user);
//    }

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
        Long userId = user.getId();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String password = user.getPassword();
        String description = user.getDescription();

        this.userRepository.updateUser(firstName, lastName, password, description, userId);
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
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUserByCompanyId(Long companyId) {
        return userRepository.findAllByCompanyId(companyId);
    }
}
