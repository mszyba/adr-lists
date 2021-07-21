package eu.michalszyba.adrlist.repository;

import eu.michalszyba.adrlist.model.Firma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirmaRepository extends JpaRepository<Firma, Long> {
}
