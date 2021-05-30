package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.Un;
import eu.michalszyba.adrlist.repository.UnRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnServiceImpl implements UnService {

    private final UnRepository unRepository;

    public UnServiceImpl(UnRepository unRepository) {
        this.unRepository = unRepository;
    }

    @Override
    public List<Un> getAllUn() {
        return unRepository.findAll();
    }
}
