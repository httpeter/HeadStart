package case1.nl.controller;

import case1.nl.entities.Place;
import case1.nl.entities.Trip;
import case1.nl.util.FMessage;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

/**
 *
 * @author PeterH
 */
@ViewScoped
@ManagedBean
public class PlacesController implements Serializable {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;

    private TimelineModel timelineModel;

    private DashboardModel dashboardModel;

    private List<Trip> trips;

    private Trip selectedTrip;

    private int selectedTripDuration;

    private List<Place> places;

    private Place selectedPlace;



    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public TimelineModel getTimelineModel() {
        return timelineModel;
    }



    public void setTimelineModel(TimelineModel timelineModel) {
        this.timelineModel = timelineModel;
    }



    public int getSelectedTripDuration() {
        return selectedTripDuration;
    }



    public void setSelectedTripDuration(int selectedTripDuration) {
        this.selectedTripDuration = selectedTripDuration;
    }



    public DashboardModel getDashboardModel() {
        return dashboardModel;
    }



    public void setDashboardModel(DashboardModel dashboardModel) {
        this.dashboardModel = dashboardModel;
    }



    public List<Trip> getTrips() {
        return trips;
    }



    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }



    public int getSelectedTripID() {
        return selectedTrip.getId();
    }



    public void setSelectedTripID(int id) {
        this.selectedTrip = trips.stream()
                .filter(trip -> trip.getId() == id)
                .findFirst()
                .get();
    }



    public Trip getSelectedTrip() {
        return selectedTrip;
    }



    public Place getSelectedPlace() {
        return selectedPlace;
    }



    public void setSelectedPlace(Place selectedPlace) {
        this.selectedPlace = selectedPlace;
    }



    public List<Place> getPlaces() {
        return places;
    }



    public void setPlaces(List<Place> places) {
        this.places = places;
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

        try {
            //Load Dashboards
            loadDasboards();

            //Load Trips
            loadTrips();

            //Load Places;
            loadPlaces();

        } catch (Exception ex) {
            Logger.getLogger(PlacesController.class.getName())
                    .log(Level.SEVERE, null, ex);
            FMessage.error(ex.getMessage());
        }

    }



    private void loadDasboards() throws Exception {
        dashboardModel = new DefaultDashboardModel();

        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();

        column1.addWidget("trips");
        column2.addWidget("map");
        column1.addWidget("timeLine");
        column1.addWidget("places");

        dashboardModel.addColumn(column1);
        dashboardModel.addColumn(column2);
    }



    private void loadTrips() throws Exception {
        trips = session.getPlacesRepository()
                .getResultList(Trip.class);

        selectedTrip = trips.get(0);
    }



    public void loadPlaces() {
        if (selectedTrip.getId() != null) {
            places = session.getPlacesRepository()
                    .getPlaces(selectedTrip.getId());

            timelineModel = new TimelineModel();

            //Filling the timeline
            places.forEach(place -> {
                timelineModel.add(TimelineEvent.<String>builder()
                        .data(place.getName())
                        .startDate(Instant.ofEpochMilli(place.getArrivaldate()
                                .getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate())
                        .build());
            });

//            selectedTripDuration = places.get(places.size()).getDeparturedate().
        } else {
            FMessage.warn("No tip found!");
        }
    }



    public void saveSelectedTrip() {
        if (selectedTrip != null
                && session.getPlacesRepository()
                        .merged(selectedTrip)) {
            FMessage.info("Trip Saved");
        } else {
            FMessage.error("Could not save trip");
        }
    }



    public void saveSelectedPlace() {
        if (selectedPlace != null
                && session.getPlacesRepository()
                        .merged(selectedPlace)) {
            FMessage.info("Place '"
                    + selectedPlace.getName()
                    + "' Saved");
        } else {
            FMessage.error("Could not save place");
        }
    }



    public void selectTimeline(TimelineSelectEvent<String> e) {

        TimelineEvent<String> timelineEvent = e.getTimelineEvent();

        places.forEach(place -> {
            if (place.getName().equals(timelineEvent.getData())) {
                selectedPlace = place;
            }
        });

        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('placesDLG').show();");

    }

}
