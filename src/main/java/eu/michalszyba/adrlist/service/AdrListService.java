package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.AdrList;

import java.util.List;

public interface AdrListService {

    List<AdrList> getAllAdrList();

    void saverAdrList(AdrList adrList);
}
