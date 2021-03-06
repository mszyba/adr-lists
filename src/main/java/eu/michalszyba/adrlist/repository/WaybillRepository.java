package eu.michalszyba.adrlist.repository;

import eu.michalszyba.adrlist.model.Waybill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaybillRepository extends JpaRepository<Waybill, Long> {
}