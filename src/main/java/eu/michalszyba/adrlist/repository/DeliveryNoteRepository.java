package eu.michalszyba.adrlist.repository;

import eu.michalszyba.adrlist.model.DeliveryNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, Long> {

    List<DeliveryNote> findAllByCompanyId(Long companyId);
}
