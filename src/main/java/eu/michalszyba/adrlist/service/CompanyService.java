package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompany();

    void saveCompany(Company company);

    void deleteCompanyById(Long id);

    Company getCompanyById(Long id);
}
