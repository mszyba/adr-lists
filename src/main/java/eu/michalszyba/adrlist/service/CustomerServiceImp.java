package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.Customer;
import eu.michalszyba.adrlist.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public void saveCustomer(Customer customer) {
        this.customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public Customer getCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public List<Customer> getAllCustomerByCompanyId(Long companyId) {
        return customerRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<Customer> getAllCustomerByUserId(Long userId) {
        return null;
    }
}
