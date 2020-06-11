package case1.nl.controller;

import case1.nl.util.FMessage;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.faces.push.PushContext;



/**
 *
 * @author PeterH
 */
@ViewScoped
@ManagedBean
public class NotesController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;
        
    PushContext helloChannel;

    String message;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SessionController getSession() {
        return session;
    }

    public void setSession(SessionController session) {
        this.session = session;
    }

    //</editor-fold>
    @PostConstruct
    public void init() {

    }

    public void sendMessage() {
        FMessage.info("send push message");
        this.sendPushMessage("hello");
    }

    private void sendPushMessage(Object message) {
        helloChannel.send("" + message + " at " + LocalDateTime.now());
    }

}
