package eu.michalszyba.adrlist.repository;

import eu.michalszyba.adrlist.model.AdrList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdrListRepository extends JpaRepository<AdrList, Long> {
}
