package case1.nl.controller;

import case1.nl.util.FMessage;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author PeterH
 */
@ViewScoped
@ManagedBean
public class FlenderreportController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    private String location = "",
            periodType = "",
            dateGenerated = "";



    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getDateGenerated() {
        return dateGenerated;
    }



    public void setDateGenerated(String dateGenerated) {
        this.dateGenerated = dateGenerated;
    }



    public SessionController getSession() {
        return session;
    }



    public void setSession(SessionController session) {

        this.session = session;
    }



    public String getPeriodType() {
        return periodType;
    }



    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }



    public String getLocation() {
        return location;
    }



    public void setLocation(String location) {

        FMessage.info(location);
        this.location = location;
    }



    //</editor-fold>
    public FlenderreportController() {
    }



    @PostConstruct
    public void init() {

    }

}
