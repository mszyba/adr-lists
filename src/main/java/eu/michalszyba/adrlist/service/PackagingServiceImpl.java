package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.Packaging;
import eu.michalszyba.adrlist.repository.PackagingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackagingServiceImpl implements PackagingService {

    public PackagingServiceImpl(PackagingRepository packagingRepository) {
        this.packagingRepository = packagingRepository;
    }

    private final PackagingRepository packagingRepository;

    @Override
    public List<Packaging> getAllPackages() {
        return packagingRepository.findAll();
    }
}
