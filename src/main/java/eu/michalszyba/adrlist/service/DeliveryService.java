package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.*;
import eu.michalszyba.adrlist.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final CompanyService companyService;
    private final CustomerService customerService;
    private final UnService unService;
    private final PackagingService packagingService;

    public DeliveryService(DeliveryRepository deliveryRepository, CompanyService companyService, CustomerService customerService, UnService unService, PackagingService packagingService) {
        this.deliveryRepository = deliveryRepository;
        this.companyService = companyService;
        this.customerService = customerService;
        this.unService = unService;
        this.packagingService = packagingService;
    }

    public Delivery save(Delivery delivery) {

        delivery.getMaterialRows().forEach(materialRow -> {
            Long unId= materialRow.getUnId();
            Un unById = unService.getUnById(unId);

            materialRow.setUnId(unById.getId());
            materialRow.setUnNameAndDescription(unById.getUnNameAndDescription());
            materialRow.setUnNumber(unById.getUnNumber());
            materialRow.setUnLabels(unById.getUnLabels());

            Long packagingId = materialRow.getPackagingId();
            Packaging packagingById = packagingService.getPackagingById(packagingId);

            materialRow.setPackagingId(packagingById.getId());
            materialRow.setPackagingCode(packagingById.getCodePackaging());
            materialRow.setPackagingDescription(packagingById.getDescriptionPackaging());

            materialRow.setDelivery(delivery);
        });

        Long companyId = delivery.getCompanyId();
        Company companyById = companyService.getCompanyById(companyId);

        Long customerId = delivery.getCustomerId();
        Customer customerById = customerService.getCustomerById(customerId);

        delivery.setCompanyAddress(companyById.getAddress());
        delivery.setCompanyCity(companyById.getCity());
        delivery.setCompanyCountry(companyById.getCountry());
        delivery.setCompanyId(companyById.getId());
        delivery.setCompanyName(companyById.getName());
        delivery.setCompanyPostcode(companyById.getPostcode());
        
        delivery.setCustomerAddress(customerById.getAddress());
        delivery.setCustomerCity(customerById.getCity());
        delivery.setCustomerCountry(customerById.getCountry());
        delivery.setCustomerId(customerById.getId());
        delivery.setCustomerName(customerById.getName());
        delivery.setCustomerPostcode(customerById.getPostcode());
        
        return this.deliveryRepository.save(delivery);
    }

    public void addMaterialRow(Delivery delivery) {
        delivery.getMaterialRows().add(new MaterialRow());
    }
}