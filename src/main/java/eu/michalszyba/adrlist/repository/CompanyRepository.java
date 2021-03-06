package eu.michalszyba.adrlist.repository;

import eu.michalszyba.adrlist.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value = "UPDATE #{#entityName} e SET e.isActive=false WHERE e.id=?1")
    @Modifying
    @Transactional
    void softDelete(Long id);
}