package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.Packaging;
import eu.michalszyba.adrlist.repository.PackagingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PackagingService {

    private final PackagingRepository packagingRepository;

    public PackagingService(PackagingRepository packagingRepository) {
        this.packagingRepository = packagingRepository;
    }

    public Packaging getPackagingById(Long id) {
        Optional<Packaging> optionalPackaging = packagingRepository.findById(id);
        if (optionalPackaging.isPresent()) {
            return optionalPackaging.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "packaging not found");
        }
    }

    public List<Packaging> getAllPackages() {
        return packagingRepository.findAll();
    }
}