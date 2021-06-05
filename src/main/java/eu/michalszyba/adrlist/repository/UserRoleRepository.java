package eu.michalszyba.adrlist.repository;

import eu.michalszyba.adrlist.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByRole(String role);
}
