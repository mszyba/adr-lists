package eu.michalszyba.adrlist.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter @Setter @NoArgsConstructor
public class UnBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String unNumber;
    private String unNameAndDescription;
    private String unClass;
    private String unClassificationCode;
    private String unPackingGroup;
    private String unLabels;
    private String unLimitedQuantityVol;
    private String unLimitedQuantityCode;
    private String unPackagingInstructions;
    private String unPackagingSpecialPP;
    private String unPackagingMixedPP;
    private String unPortableTanksInstructions;
    private String unPortableTanksSpecialP;
    private String unAdrTankCode;
    private String unAdrTankSpecialP;
    private String unVehicleForTankCarriage;
    private String unTransportCategory;
    private String unSpecialPFCPackages;
    private String unSpecialPFCBulk;
    private String unSpecialPFCLoading;
    private String unSpecialPFCOperation;
    private String unHazardIdNo;
}