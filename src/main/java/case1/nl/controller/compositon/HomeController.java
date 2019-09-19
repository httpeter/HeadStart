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
import javax.faces.bean.SessionScoped;

/**
 *
 * @author PeterH
 */
@SessionScoped
@ManagedBean
public class HomeController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;    

    private List persons = new ArrayList<Person>();

    private Person selectedPerson = new Person();
    

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
        } catch (Exception e) {
            FMessage.error(e.getMessage());            
        }
    }

    public void savePerson() {
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
        selectedPerson.setFirstName("blaat");
        selectedPerson.setLastName("asdf");
        selectedPerson.setAge("33");
        selectedPerson.setGender("m");
        selectedPerson.setNotes("testing123");
        FMessage.info(selectedPerson.getFirstName());
    }

    public void deletePerson() {

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
