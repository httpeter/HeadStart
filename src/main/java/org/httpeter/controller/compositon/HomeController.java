package org.httpeter.controller.compositon;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.httpeter.controller.SessionController;
import org.httpeter.entities.Person;
import org.httpeter.util.FMessage;
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
    private SessionController session;

    private DashboardModel dashboardModel;

    private List persons;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public SessionController getSession() {
        return session;
    }

    public void setSession(SessionController session) {
        this.session = session;
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
        persons = session.getDB().getResultList(Person.class);
        if (persons != null) {
            FMessage.info("DB connection established, persons loaded.");

        }
    }

}
