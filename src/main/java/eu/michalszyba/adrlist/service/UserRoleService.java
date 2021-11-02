package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.UserRole;
import eu.michalszyba.adrlist.repository.UserRoleRepository;

public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    UserRole getUserRoleByRole(String role) {
        return userRoleRepository.findByRole(role);
    }
}
