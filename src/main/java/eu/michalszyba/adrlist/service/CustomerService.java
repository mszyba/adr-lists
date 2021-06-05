package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomer();

    void saveCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomerById(Long id);

    Customer getCustomerById(Long id);

    List<Customer> getAllCustomerByCompanyId(Long companyId);

    List<Customer> getAllCustomerByUserId(Long userId);
}
