package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.*;
import eu.michalszyba.adrlist.repository.DeliveryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
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

        var ref = new Object() {
            Integer pointsClass1 = 0;
            Integer pointsClass2 = 0;
            Integer pointsClass3 = 0;
            Integer pointsAll = 0;
        };


        delivery.getMaterialRows().forEach(materialRow -> {
            Long unId= materialRow.getUnId();
            Un unById = unService.getUnById(unId);

            String unPackingGroup = unById.getUnPackingGroup();
            Integer quantityAll = materialRow.getQuantityAll();


            /*
            * check each Packing Group and add for each result of points
            * */
            switch (unPackingGroup) {
                case "I":
                    ref.pointsClass1 += 6 * quantityAll;
                    ref.pointsAll += ref.pointsClass1;
                    break;
                case "II":
                    ref.pointsClass2 += 3 * quantityAll;
                    ref.pointsAll += ref.pointsClass2;
                    break;
                case "III":
                    ref.pointsClass3 += quantityAll;
                    ref.pointsAll += ref.pointsClass3;
                    break;
            }

            delivery.setPointClass1(ref.pointsClass1);
            delivery.setPointClass2(ref.pointsClass2);
            delivery.setPointClass3(ref.pointsClass3);
            delivery.setPoints(ref.pointsAll);


            materialRow.setUnId(unById.getId());
            materialRow.setUnNameAndDescription(unById.getUnNameAndDescription());
            materialRow.setUnNumber(unById.getUnNumber());
            materialRow.setUnLabels(unById.getUnLabels());
            materialRow.setUnPackingGroup(unById.getUnPackingGroup());


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

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    public Delivery getOneDelivery(Long id) {
        return deliveryRepository.findById(id).orElseThrow();
    }
}