package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.UserRole;
import eu.michalszyba.adrlist.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository roleRepository;

    public UserRoleServiceImpl(UserRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRole getUserRoleByRole(String role) {
        return roleRepository.findByRole(role);
    }
}
