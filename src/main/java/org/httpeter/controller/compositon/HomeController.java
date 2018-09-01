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

    private Person selectedPerson;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Person getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
    }

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

        DashboardColumn column1 = new DefaultDashboardColumn();
        column1.addWidget("welcomePanel");
        column1.addWidget("demoPanel");

        DashboardColumn column2 = new DefaultDashboardColumn();

        dashboardModel = new DefaultDashboardModel();
        dashboardModel.addColumn(column1);
        dashboardModel.addColumn(column2);
    }

    public void loadPersons() {
        persons = session.getDB().getResultList(Person.class);
//        selectedPerson = (Person) persons.get(0);
        newPerson();
    }

    public void saveSelectedPerson() {
        if (session.getDB().persisted(selectedPerson)) {
            FMessage.info(selectedPerson.getFirstName()
                    + " "
                    + selectedPerson.getLastName()
                    + " Saved");
            loadPersons();
        } else {
            FMessage.error(session.getLabels().getProperty("msgPersonSaveError"));
        }
    }

    public void newPerson() {
        selectedPerson = new Person();
    }

    public void deleteSelectedPerson() {
        
        if (session.getDB().deleted(selectedPerson)) {
            FMessage.info(selectedPerson.getFirstName()
                    + " "
                    + selectedPerson.getLastName()
                    + " Deleted");
            loadPersons();
        } else {
            FMessage.error(session.getLabels().getProperty("msgPersonDeleteError"));
        }
    }

}
