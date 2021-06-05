package eu.michalszyba.adrlist.repository;

import eu.michalszyba.adrlist.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByCompanyId(Long id);

    @Query(value = "SELECT c FROM Customer c WHERE c.id = :id")
    Optional<Customer> findCustomerById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Customer c SET c.address = :address, c.city = :city, c.customerName = :customerName, c.email = :email, c.personContact = :personContact, c.phone = :phone, c.postCode = :postCode, c.referenceNo = :referenceNo WHERE c.id = :id")
    void updateCustomer(@Param("address") String address,
                        @Param("city") String city,
                        @Param("customerName") String customerName,
                        @Param("email") String email,
                        @Param("personContact") String personContact,
                        @Param("phone") String phone,
                        @Param("postCode") String postCode,
                        @Param("referenceNo") String referenceNo,
                        @Param("id") Long is);
}
