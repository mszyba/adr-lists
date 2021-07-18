package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    public void saveCompany(Company company) {
        this.companyRepository.save(company);
    }

    public void softDeleteCompanyById(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            this.companyRepository.softDelete(id);
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Company getCompanyById(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            return optionalCompany.get();
        } else {
            throw new EntityNotFoundException();
        }
    }
}
