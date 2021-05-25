package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.AdrList;
import eu.michalszyba.adrlist.repository.AdrListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdrListServiceImpl implements AdrListService {

    private final AdrListRepository adrListRepository;

    public AdrListServiceImpl(AdrListRepository adrListRepository) {
        this.adrListRepository = adrListRepository;
    }

    @Override
    public List<AdrList> getAllAdrList() {
        return adrListRepository.findAll();
    }

    @Override
    public void saverAdrList(AdrList adrList) {
        adrListRepository.save(adrList);
    }
}
