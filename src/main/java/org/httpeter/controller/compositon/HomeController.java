package org.httpeter.controller.compositon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.httpeter.data.repository.DefaultRepository;
import org.httpeter.entities.Person;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

/**
 *
 * @author PeterH
 */
@ManagedBean
@ViewScoped
public class HomeController implements Serializable {

    private DashboardModel dashboardModel;    

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public DashboardModel getDashboardModel() {
        return dashboardModel;
    }

    public void setDashboardModel(DashboardModel dashboardModel) {
        this.dashboardModel = dashboardModel;
    }
//</editor-fold>

    public HomeController() {
        dashboardModel = new DefaultDashboardModel();

        DashboardColumn column1 = new DefaultDashboardColumn();

        column1.addWidget("dashboardInfoPanel");
        column1.addWidget("testPanel");

        dashboardModel.addColumn(column1);

    }

    public List getPersons() {
        List<Person> pl = new ArrayList();                              
        return pl;
    }
    
    public void test()
    {
        System.out.println(DefaultRepository.getInstance("PU").getResultList(Person.class));
    }

}
