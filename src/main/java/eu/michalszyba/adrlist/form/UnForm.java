package eu.michalszyba.adrlist.form;

import eu.michalszyba.adrlist.model.Packaging;
import eu.michalszyba.adrlist.model.Un;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class UnForm {

    private List<Un> unList;
    private Long quantityPiece;
    private List<Packaging> packagingList;
    private Long quantityAll;
    private String unDesc;
}
