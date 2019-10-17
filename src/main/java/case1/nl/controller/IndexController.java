package case1.nl.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import case1.nl.data.repository.DefaultRepository;
import case1.nl.entities.Person;
import case1.nl.util.FMessage;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author PeterH
 */
@SessionScoped
@ManagedBean
public class IndexController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    private List persons = new ArrayList<Person>();

    private Person selectedPerson = new Person();

    private Person newPerson = new Person();

    private final DefaultRepository defaultRepository = new DefaultRepository("PU");

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
            persons = defaultRepository.getResultList(Person.class);
//            selectedPerson = (Person) persons.get(0);
        } catch (Exception e) {
            FMessage.error(e.getMessage());
        }
    }
    
     public void selectPerson(SelectEvent event) {
         Person p = (Person) event.getObject();                  
         selectedPerson = (Person) event.getObject(); 

    }
 

    public void saveSelectedPerson() {
        if (defaultRepository.persisted(selectedPerson)) {
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
        if (defaultRepository.persisted(newPerson)) {
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

        if (defaultRepository.deleted(selectedPerson)) {
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
