package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.Customer;
import eu.michalszyba.adrlist.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public void saveCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }

    public void deleteCustomerById(Long id) {
        this.customerRepository.deleteById(id);
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    public List<Customer> getAllCustomerByCompanyId(Long companyId) {
        return customerRepository.findAllByCompanyId(companyId);
    }

    public List<Customer> getAllCustomerByUserId(Long userId) {
        return null;
    }

//    public void updateCustomer(Customer customer) {
//        customerRepository.updateCustomer(
//                customer.getAddress(),
//                customer.getCity(),
//                customer.getName(),
//                customer.getEmail(),
//                customer.getPersonContact(),
//                customer.getPhone(),
//                customer.getPostcode(),
//                customer.getReferenceNo(),
//                customer.getId()
//        );
//    }
}