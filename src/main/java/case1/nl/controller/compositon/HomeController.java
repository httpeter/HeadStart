package case1.nl.controller.compositon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import case1.nl.controller.SessionController;
import case1.nl.entities.Person;
import case1.nl.util.FMessage;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author PeterH
 */
@ViewScoped
@ManagedBean
public class HomeController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    private List persons = new ArrayList<Person>();

    private Person selectedPerson = new Person();

    private Person newPerson = new Person();

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Person getNewPerson() {
        return newPerson;
    }

    public void setNewPerson(Person newPerson) {
        this.newPerson = newPerson;
    }

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

    public List getPersons() {
        return persons;
    }

    public void setPersons(List persons) {
        this.persons = persons;
    }

//</editor-fold>  
    @PostConstruct
    public void loadPersons() {
        try {
            persons = session.getDB().getResultList(Person.class);
            selectedPerson = (Person) persons.get(0);
        } catch (Exception e) {
            FMessage.error(e.getMessage());
        }
    }

    public void saveSelectedPerson() {
        if (session.getDB().persisted(selectedPerson)) {
            FMessage.info(selectedPerson.getFirstname()
                    + " "
                    + selectedPerson.getLastname()
                    + " Saved");
            loadPersons();
        } else {
            FMessage.error(session.getLabels().getProperty("msgPersonSaveError"));
        }
    }
    
    public void saveNewPerson() {
        if (session.getDB().persisted(newPerson)) {
            FMessage.info(newPerson.getFirstname()
                    + " "
                    + newPerson.getLastname()
                    + " Saved");
            loadPersons();
        } else {
            FMessage.error(session.getLabels().getProperty("msgPersonSaveError"));
        }
    }

    public void newPerson() {
        newPerson = new Person();       
    }

    public void deletePerson() {

        if (session.getDB().deleted(selectedPerson)) {
            FMessage.info(selectedPerson.getFirstname()
                    + " "
                    + selectedPerson.getLastname()
                    + " Deleted");
            loadPersons();
        } else {
            FMessage.error(session.getLabels().getProperty("msgPersonDeleteError"));
        }
    }

}
