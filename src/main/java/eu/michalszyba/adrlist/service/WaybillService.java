package eu.michalszyba.adrlist.service;

import eu.michalszyba.adrlist.model.*;
import eu.michalszyba.adrlist.repository.WaybillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WaybillService {

    private final WaybillRepository waybillRepository;
    private final CompanyService companyService;
    private final CustomerService customerService;
    private final UnService unService;
    private final PackagingService packagingService;

    public WaybillService(WaybillRepository waybillRepository, CompanyService companyService, CustomerService customerService, UnService unService, PackagingService packagingService) {
        this.waybillRepository = waybillRepository;
        this.companyService = companyService;
        this.customerService = customerService;
        this.unService = unService;
        this.packagingService = packagingService;
    }

    public Waybill save(Waybill waybill) {

        var ref = new Object() {
            Integer pointsClass1 = 0;
            Integer pointsClass2 = 0;
            Integer pointsClass3 = 0;
            Integer pointsAll = 0;
        };


        waybill.getMaterialRows().forEach(materialRow -> {
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

            waybill.setPointClass1(ref.pointsClass1);
            waybill.setPointClass2(ref.pointsClass2);
            waybill.setPointClass3(ref.pointsClass3);
            waybill.setPoints(ref.pointsAll);


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

            materialRow.setWaybill(waybill);
        });

        Long companyId = waybill.getCompanyId();
        Company companyById = companyService.getCompanyById(companyId);

        Long customerId = waybill.getCustomerId();
        Customer customerById = customerService.getCustomerById(customerId);

        waybill.setCompanyAddress(companyById.getAddress());
        waybill.setCompanyCity(companyById.getCity());
        waybill.setCompanyCountry(companyById.getCountry());
        waybill.setCompanyId(companyById.getId());
        waybill.setCompanyName(companyById.getName());
        waybill.setCompanyPostcode(companyById.getPostcode());
        
        waybill.setCustomerAddress(customerById.getAddress());
        waybill.setCustomerCity(customerById.getCity());
        waybill.setCustomerCountry(customerById.getCountry());
        waybill.setCustomerId(customerById.getId());
        waybill.setCustomerName(customerById.getName());
        waybill.setCustomerPostcode(customerById.getPostcode());
        
        return this.waybillRepository.save(waybill);
    }

    public void addMaterialRow(Waybill waybill) {
        waybill.getMaterialRows().add(new MaterialRow());
    }

    public List<Waybill> getAllWaybill() {
        return waybillRepository.findAll();
    }

    public Waybill getOneWaybillById(Long id) {
        return waybillRepository.findById(id).orElseThrow();
    }
}