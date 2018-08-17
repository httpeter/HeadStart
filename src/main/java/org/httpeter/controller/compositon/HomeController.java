package org.httpeter.controller.compositon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.httpeter.controller.SessionController;
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

    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;

    private DashboardModel dashboardModel;

    private List persons;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public DashboardModel getDashboardModel() {
        return dashboardModel;
    }

    public void setDashboardModel(DashboardModel dashboardModel) {
        this.dashboardModel = dashboardModel;
    }

    public List getPersons() {
        return persons;
    }

    public void setPersons(List persons) {
        this.persons = persons;
    }

//</editor-fold>
    public HomeController() {
        dashboardModel = new DefaultDashboardModel();

        DashboardColumn column1 = new DefaultDashboardColumn();

        column1.addWidget("dashboardInfoPanel");
        column1.addWidget("testPanel");

        dashboardModel.addColumn(column1);
    }

    public void loadPersons() {
        persons = sessionController.getDB().getResultList(Person.class);
    }

}
