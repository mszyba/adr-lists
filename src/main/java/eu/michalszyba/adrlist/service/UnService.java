package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.Un;
import eu.michalszyba.adrlist.repository.UnRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnService {

    private final UnRepository unRepository;

    public UnService(UnRepository unRepository) {
        this.unRepository = unRepository;
    }

    public List<Un> getAllUn() {
        return unRepository.findAll();
    }
}
