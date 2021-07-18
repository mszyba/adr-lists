package eu.michalszyba.adrlist.repository;

import eu.michalszyba.adrlist.model.MaterialRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRowRepository extends JpaRepository<MaterialRow, Long> {
}
