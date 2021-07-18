package eu.michalszyba.adrlist.repository;

import eu.michalszyba.adrlist.model.Un;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnRepository extends JpaRepository<Un, Long> {
}