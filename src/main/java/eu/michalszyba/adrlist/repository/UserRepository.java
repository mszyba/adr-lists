package eu.michalszyba.adrlist.repository;

import eu.michalszyba.adrlist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query(value = "UPDATE #{#entityName} e SET e.isActive=false WHERE e.id=?1")
    @Modifying
    @Transactional
    void softDelete(Long id);

    List<User> findAllByCompanyId(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName, u.password = :password, u.description = :description WHERE u.id = :id")
    void updateUser(@Param("firstName") String firstName,
                    @Param("lastName") String lastName,
                    @Param("password") String password,
                    @Param("description") String description,
                    @Param("id") Long id);
}
