package case1.nl.controller;

import case1.nl.entities.Place;
import case1.nl.entities.Trip;
import case1.nl.util.FMessage;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
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

    private boolean selectable = true;
    private boolean zoomable = true;
    private boolean moveable = true;
    private boolean stackEvents = true;
    private String eventStyle = "box";
    private boolean axisOnTop;
    private boolean showCurrentTime = true;
    private boolean showNavigation = false;

    private List<Trip> trips;

    private Trip selectedTrip;

    private List<Place> places;

    private Place selectedPlace;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
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

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public boolean isStackEvents() {
        return stackEvents;
    }

    public void setStackEvents(boolean stackEvents) {
        this.stackEvents = stackEvents;
    }

    public boolean isShowCurrentTime() {
        return showCurrentTime;
    }

    public void setShowCurrentTime(boolean showCurrentTime) {
        this.showCurrentTime = showCurrentTime;
    }

    public boolean isShowNavigation() {
        return showNavigation;
    }

    public void setShowNavigation(boolean showNavigation) {
        this.showNavigation = showNavigation;
    }

    public boolean isZoomable() {
        return zoomable;
    }

    public void setZoomable(boolean zoomable) {
        this.zoomable = zoomable;
    }

    public boolean isAxisOnTop() {
        return axisOnTop;
    }

    public void setAxisOnTop(boolean axisOnTop) {
        this.axisOnTop = axisOnTop;
    }

    public TimelineModel getTimelineModel() {
        return timelineModel;
    }

    public void setTimelineModel(TimelineModel timelineModel) {
        this.timelineModel = timelineModel;
    }

    public boolean isMoveable() {
        return moveable;
    }

    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    public String getEventStyle() {
        return eventStyle;
    }

    public void setEventStyle(String eventStyle) {
        this.eventStyle = eventStyle;
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

        loadDasboards();

        loadTripsAndPlaces();

        timelineModel = new TimelineModel();

        loadTripsAndPlaces();

        places.forEach(place -> {
            timelineModel.add(TimelineEvent.<String>builder()
                    .data(place.getName())
                    .startDate(LocalDate.of(2014, 6, 12))
                    .build());
        });

    }

    private void loadDasboards() {
        dashboardModel = new DefaultDashboardModel();
        DashboardColumn columnn1 = new DefaultDashboardColumn();
        columnn1.addWidget("trips");
        DashboardColumn column2 = new DefaultDashboardColumn();
        column2.addWidget("timeLine");

        columnn1.addWidget("map");
        dashboardModel.addColumn(columnn1);

        column2.addWidget("places");
        dashboardModel.addColumn(column2);
    }

    public void loadTripsAndPlaces() {
        trips = session.getPlacesRepository().getResultList(Trip.class);

        places = session.getPlacesRepository().getResultList(Place.class);

        selectedTrip = trips.get(0);
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

}
