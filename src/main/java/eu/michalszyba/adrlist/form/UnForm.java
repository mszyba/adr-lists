package eu.michalszyba.adrlist.form;

import eu.michalszyba.adrlist.model.Packaging;
import eu.michalszyba.adrlist.model.Un;

import java.util.List;

public class UnForm {

    private List<Un> unList;
    private Long quantityPiece;
    private List<Packaging> packagingList;
    private Long quantityAll;
    private String unDesc;

    public UnForm() {
    }

    public List<Un> getUnList() {
        return unList;
    }

    public void setUnList(List<Un> unList) {
        this.unList = unList;
    }

    public Long getQuantityPiece() {
        return quantityPiece;
    }

    public void setQuantityPiece(Long quantityPiece) {
        this.quantityPiece = quantityPiece;
    }

    public List<Packaging> getPackagingList() {
        return packagingList;
    }

    public void setPackagingList(List<Packaging> packagingList) {
        this.packagingList = packagingList;
    }

    public Long getQuantityAll() {
        return quantityAll;
    }

    public void setQuantityAll(Long quantityAll) {
        this.quantityAll = quantityAll;
    }

    public String getUnDesc() {
        return unDesc;
    }

    public void setUnDesc(String unDesc) {
        this.unDesc = unDesc;
    }

    @Override
    public String toString() {
        return "UnForm{" +
                "unList=" + unList +
                ", quantityPiece=" + quantityPiece +
                ", packagingList=" + packagingList +
                ", quantityAll=" + quantityAll +
                ", unDesc='" + unDesc + '\'' +
                '}';
    }
}
