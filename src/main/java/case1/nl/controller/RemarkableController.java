package case1.nl.controller;



import case1.nl.util.FMessage;
import case1.nl.util.Tabricator;
import java.io.File;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author PeterH
 */
@ViewScoped
@ManagedBean
public class RemarkableController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    private String remarkup;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getRemarkup() {
        return remarkup;
    }

    public void setRemarkup(String remarkup) {
        this.remarkup = remarkup;
    }

    public SessionController getSession() {
        return session;
    }

    public void setSession(SessionController session) {
        this.session = session;
    }

    //</editor-fold>
    public RemarkableController() {
    }

    @PostConstruct
    public void init() {

    }

    public void handleFileUpload(FileUploadEvent event) {

        if (event.getFile() != null) {
            FMessage.info("Successful" + event.getFile().getFileName() + " is uploaded.");

            Tabricator t = new Tabricator();

            remarkup = t.getRemarkupTable(new String(event.getFile().getContents()));

        } else {
            FMessage.error("Upload failed");
        }
    }

}
