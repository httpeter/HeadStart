package case1.nl.controller;

import case1.nl.entities.Syspage;
import case1.nl.entities.User;
import case1.nl.util.FMessage;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author PeterH
 */
@SessionScoped
@ManagedBean
public class AdminController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    private List<User> users;

    private User selectedUser;

    private User newUser;

    private List<Syspage> sysPages;

    private int userTabIndex = 0;


    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">

    public int getUserTabIndex() {
        return userTabIndex;
    }



    public void setUserTabIndex(int userTabIndex) {
        this.userTabIndex = userTabIndex;
    }



    public List<Syspage> getSyspages() {
        return sysPages;
    }



    public void setSyspages(List<Syspage> sysPages) {
        this.sysPages = sysPages;
    }



    public User getNewUser() {
        return newUser;
    }



    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }



    public User getSelectedUser() {
        return selectedUser;
    }



    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }



    public SessionController getSession() {
        return session;
    }



    public void setSession(SessionController session) {
        this.session = session;
    }



    public List<User> getUsers() {
        return users;
    }



    public void setUsers(List<User> users) {
        this.users = users;
    }
//</editor-fold>



    @PostConstruct
    private void init() {
        this.loadUsersAndSysPages();
    }



    public void loadUsersAndSysPages() {
        sysPages = session.getUserRepository()
                .findAll(Syspage.class);

        users = session.getUserRepository()
                .findAll(User.class);
    }



    public void makeNewUser() {
        newUser = new User();
        newUser.setRole("user");
    }



    public void saveNewUser() {

        if (session.getUserRepository().persisted(newUser)) {
            FMessage.info(newUser.getFirstname()
                    + " "
                    + newUser.getLastname()
                    + " saved.");
            this.init();
        } else {
            FMessage.fatal("Could not save "
                    + newUser.getFirstname()
                    + " "
                    + newUser.getLastname());
        }
        this.loadUsersAndSysPages();

    }



    public void selectUser(SelectEvent selectEvent) {

        try {
            selectedUser = (User) selectEvent.getObject();
            userTabIndex = 1;
        } catch (Exception e) {
            Logger.getLogger(AdminController.class.getName())
                    .log(Level.SEVERE, null, e);
            FMessage.error(e.getMessage());
            userTabIndex = 0;
        }
    }



    public void saveUser() {

        if (session.getUserRepository().persisted(selectedUser)) {

            FMessage.info(selectedUser.getFirstname()
                    + " "
                    + selectedUser.getLastname()
                    + " saved.");
            selectedUser = null;
        } else {
            FMessage.error("Could not save user "
                    + selectedUser.getFirstname()
                    + " "
                    + selectedUser.getLastname());
        }
        this.loadUsersAndSysPages();

    }



    public void deleteUser() {

        if (session.getUserRepository().deleted(selectedUser)) {
            FMessage.info(selectedUser.getFirstname()
                    + " "
                    + selectedUser.getLastname()
                    + " Deleted");
        } else {
            FMessage.error(session.getLabels().getProperty("msgPersonDeleteError"));
        }
        this.loadUsersAndSysPages();
    }

}
