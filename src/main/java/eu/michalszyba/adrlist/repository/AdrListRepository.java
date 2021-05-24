package eu.michalszyba.adrlist.repository;

import eu.michalszyba.adrlist.model.AdrListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdrListRepository extends JpaRepository<AdrListEntity, Long> {
}
