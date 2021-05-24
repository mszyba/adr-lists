package eu.michalszyba.adrlist.form;

import eu.michalszyba.adrlist.model.Un;

import java.util.List;

public class UnForm {

    private List<Un> unList;
    private String unDesc;

    public UnForm() {
    }

    public UnForm(List<Un> unList, String unDesc) {
        this.unList = unList;
        this.unDesc = unDesc;
    }

    public List<Un> getUnList() {
        return unList;
    }

    public void setUnList(List<Un> unList) {
        this.unList = unList;
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
                ", unDesc='" + unDesc + '\'' +
                '}';
    }
}
