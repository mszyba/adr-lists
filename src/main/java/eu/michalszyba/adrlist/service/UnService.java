package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.Un;
import eu.michalszyba.adrlist.repository.UnRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UnService {

    private final UnRepository unRepository;

    public UnService(UnRepository unRepository) {
        this.unRepository = unRepository;
    }

    public Un getUnById(Long id) {
        Optional<Un> optionalUn = unRepository.findById(id);
        if (optionalUn.isPresent()) {
            return optionalUn.get();
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    public List<Un> getAllUn() {
        return unRepository.findAll();
    }
}