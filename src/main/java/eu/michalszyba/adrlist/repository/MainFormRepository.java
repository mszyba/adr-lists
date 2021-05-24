package eu.michalszyba.adrlist.repository;

import eu.michalszyba.adrlist.form.MainForm;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MainFormRepository {

    private final List<MainForm> mainFormList = new ArrayList<>();

    public List<MainForm> findAll() {
        return new ArrayList<MainForm>(this.mainFormList);
    }

    public void add(MainForm mainForm) {
        this.mainFormList.add(mainForm);
    }
}
