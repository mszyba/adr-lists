package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.Company;
import eu.michalszyba.adrlist.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public void saveCompany(Company company) {
        this.companyRepository.save(company);
    }

    @Override
    public void deleteCompanyById(Long id) {
        this.companyRepository.deleteById(id);
    }

    @Override
    public Company getCompanyById(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            return optionalCompany.get();
        } else {
            throw new EntityNotFoundException();
        }
    }
}
