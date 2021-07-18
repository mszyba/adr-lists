package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.Packaging;
import eu.michalszyba.adrlist.repository.PackagingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackagingService {

    private final PackagingRepository packagingRepository;

    public PackagingService(PackagingRepository packagingRepository) {
        this.packagingRepository = packagingRepository;
    }

    public List<Packaging> getAllPackages() {
        return packagingRepository.findAll();
    }
}