package eu.michalszyba.adrlist.model;

import javax.persistence.*;

@Entity
@Table(name = "un_table")
public class Un {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String unNumber;
    private String nameAndDescription;
    private String unClass;
    private String classificationCode;
    private String packingGroup;
    private String labels;
    private String limitedQuantityVol;
    private String limitedQuantityCode;
    private String packagingInstructions;
    private String packagingSpecialPP;
    private String packagingMixedPP;
    private String portableTanksInstructions;
    private String portableTanksSpecialP;
    private String adrTankCode;
    private String adrTankSpecialP;
    private String vehicleForTankCarriage;
    private String transportCategory;
    private String specialPFCPackages;
    private String specialPFCBulk;
    private String specialPFCLoading;
    private String specialPFCOperation;
    private String hazardIdNo;

    public Un() {
    }

    public Long getId() {
        return id;
    }

    public String getUnNumber() {
        return unNumber;
    }

    public String getNameAndDescription() {
        return nameAndDescription;
    }

    public String getUnClass() {
        return unClass;
    }

    public String getClassificationCode() {
        return classificationCode;
    }

    public String getPackingGroup() {
        return packingGroup;
    }

    public String getLabels() {
        return labels;
    }

    public String getLimitedQuantityVol() {
        return limitedQuantityVol;
    }

    public String getLimitedQuantityCode() {
        return limitedQuantityCode;
    }

    public String getPackagingInstructions() {
        return packagingInstructions;
    }

    public String getPackagingSpecialPP() {
        return packagingSpecialPP;
    }

    public String getPackagingMixedPP() {
        return packagingMixedPP;
    }

    public String getPortableTanksInstructions() {
        return portableTanksInstructions;
    }

    public String getPortableTanksSpecialP() {
        return portableTanksSpecialP;
    }

    public String getAdrTankCode() {
        return adrTankCode;
    }

    public String getAdrTankSpecialP() {
        return adrTankSpecialP;
    }

    public String getVehicleForTankCarriage() {
        return vehicleForTankCarriage;
    }

    public String getTransportCategory() {
        return transportCategory;
    }

    public String getSpecialPFCPackages() {
        return specialPFCPackages;
    }

    public String getSpecialPFCBulk() {
        return specialPFCBulk;
    }

    public String getSpecialPFCLoading() {
        return specialPFCLoading;
    }

    public String getSpecialPFCOperation() {
        return specialPFCOperation;
    }

    public String getHazardIdNo() {
        return hazardIdNo;
    }

    @Override
    public String toString() {
        return "Un{" +
                "id=" + id +
                ", unNumber='" + unNumber + '\'' +
                ", nameAndDescription='" + nameAndDescription + '\'' +
                ", unClass='" + unClass + '\'' +
                ", classificationCode='" + classificationCode + '\'' +
                ", packingGroup='" + packingGroup + '\'' +
                ", labels='" + labels + '\'' +
                ", limitedQuantityVol='" + limitedQuantityVol + '\'' +
                ", limitedQuantityCode='" + limitedQuantityCode + '\'' +
                ", packagingInstructions='" + packagingInstructions + '\'' +
                ", packagingSpecialPP='" + packagingSpecialPP + '\'' +
                ", packagingMixedPP='" + packagingMixedPP + '\'' +
                ", portableTanksInstructions='" + portableTanksInstructions + '\'' +
                ", portableTanksSpecialP='" + portableTanksSpecialP + '\'' +
                ", adrTankCode='" + adrTankCode + '\'' +
                ", adrTankSpecialP='" + adrTankSpecialP + '\'' +
                ", vehicleForTankCarriage='" + vehicleForTankCarriage + '\'' +
                ", transportCategory='" + transportCategory + '\'' +
                ", specialPFCPackages='" + specialPFCPackages + '\'' +
                ", specialPFCBulk='" + specialPFCBulk + '\'' +
                ", specialPFCLoading='" + specialPFCLoading + '\'' +
                ", specialPFCOperation='" + specialPFCOperation + '\'' +
                ", hazardIdNo='" + hazardIdNo + '\'' +
                '}';
    }
}
