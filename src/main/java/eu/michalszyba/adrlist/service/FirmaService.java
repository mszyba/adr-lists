package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.Firma;
import eu.michalszyba.adrlist.repository.FirmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirmaService {

    private final FirmaRepository firmaRepository;

    public FirmaService(FirmaRepository firmaRepository) {
        this.firmaRepository = firmaRepository;
    }

    public List<Firma> findAll() {
//        List<Firma> firmaList = new ArrayList<>();
//        firmaList.add(new Firma(1L, "dddd"));
//        firmaList.add(new Firma(2L, "eeee"));
//        firmaList.add(new Firma(3L, "rrrrrr"));
//        return firmaList;
        return firmaRepository.findAll();
    }
}
