package case1.nl.controller;

import case1.nl.entities.User;
import case1.nl.util.FMessage;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author PeterH
 */
@ViewScoped
@ManagedBean
public class AdminController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    private List<User> users;

    private User selectedUser;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
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
        users = session.getUserRepository().getResultList(User.class);
    }

    public void selectUser(SelectEvent selectEvent) {
        selectedUser = (User) selectEvent.getObject();
        try {
            if (selectedUser.getPwdhash().endsWith("==")) {
                selectedUser.setPwdhash(session.getCryptor().decrypt(selectedUser.getPwdhash()));
            }
        } catch (IOException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            FMessage.error(ex.getMessage());
        }
    }

    public void saveUser() {
        try {
            selectedUser.setPwdhash(session.getCryptor().encrypt(selectedUser.getPwdhash()));
        } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (selectedUser != null
                && session.getUserRepository().persisted(selectedUser)) {

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
    }

}
