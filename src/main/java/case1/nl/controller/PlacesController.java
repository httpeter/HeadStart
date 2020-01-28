package case1.nl.controller;

import case1.nl.entities.Place;
import java.io.Serializable;
import java.util.List;
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
public class PlacesController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    private List<Place> places;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public List getPlaces() {
        return places;
    }

    public void setPlaces(List places) {
        this.places = places;
    }

    public SessionController getSession() {
        return session;
    }

    public void setSession(SessionController session) {
        this.session = session;
    }

    //</editor-fold>
    public PlacesController() {
    }

    @PostConstruct
    public void init() {

        loadPlaces();

    }

    public void loadPlaces() {
        places = session.getPlacesRepository().getResultList(Place.class);
    }
}
