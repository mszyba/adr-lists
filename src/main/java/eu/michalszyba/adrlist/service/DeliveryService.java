package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.Delivery;
import eu.michalszyba.adrlist.model.MaterialRow;
import eu.michalszyba.adrlist.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public Delivery save(Delivery delivery) {
        delivery.getMaterialRows().forEach(m -> m.setDelivery(delivery));
        return this.deliveryRepository.save(delivery);
    }

    public void addMaterialRow(Delivery delivery) {
        delivery.getMaterialRows().add(new MaterialRow());
    }
}