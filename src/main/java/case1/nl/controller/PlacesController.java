package case1.nl.controller;

import case1.nl.entities.Place;
import case1.nl.entities.Trip;
import case1.nl.util.FMessage;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
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
    
    private List<Trip> trips;
    
    private Trip selectedTrip;
    
    private int selectedTripDuration;
    
    private List<Place> places;
    
    private Place selectedPlace;
    
    private Place newPlace;
    
    private int tabIndex = 0;
    
    private MapModel mapModel;



    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public MapModel getMapModel() {
        return mapModel;
    }
    
    
    
    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }
    
    
    
    public Place getNewPlace() {
        return newPlace;
    }
    
    
    
    public void setNewPlace(Place newPlace) {
        this.newPlace = newPlace;
    }
    
    
    
    public int getTabIndex() {
        return tabIndex;
    }
    
    
    
    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }
    
    
    
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

            //Load Trips
            loadTrips();
            
        } catch (Exception ex) {
            Logger.getLogger(PlacesController.class.getName())
                    .log(Level.SEVERE, null, ex);
            FMessage.error(ex.getMessage());
        }
        
    }
    
    
    
    private void loadTrips() throws Exception {
        trips = session.getPlacesRepository()
                .getResultList(Trip.class);

        //selectedTrip = trips.get(0);
        selectedTrip = new Trip();
        selectedTrip.setId(0);
    }
    
    
    
    public void loadPlaces() {
        if (selectedTrip.getId() != null) {
            places = session.getPlacesRepository()
                    .getPlaces(selectedTrip.getId());
            
            timelineModel = new TimelineModel();
            
            mapModel = new DefaultMapModel();
            
            places.forEach(place -> {

                //Filling the timeline model
                timelineModel.add(TimelineEvent.<String>builder()
                        .data(place.getName())
                        .startDate(Instant.ofEpochMilli(place.getArrivaldate()
                                .getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate())
                        .build());

                //Filling the map model
                if (place.getLat() != null && place.getLng() != null) {
                    LatLng coord = new LatLng(Double.parseDouble(place.getLat()),
                            Double.parseDouble(place.getLng()));
                    FMessage.info("Loaded place: '" + place.getName() + "'");
                    mapModel.addOverlay(new Marker(coord, place.getName()));                    
                }
            });

//            selectedTripDuration = places.get(places.size()).getDeparturedate().
        } else {
            FMessage.warn("No tip found!");
        }
    }
    
    
    public void mapSelect(PointSelectEvent event) {
        LatLng latlng = event.getLatLng();
        FMessage.info("Selected: " +event.getLatLng());
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
            loadPlaces();
        } else {
            FMessage.error("Could not save place");
        }
    }
    
    
    
    public void deleteSelectedPlace() {
        if (selectedPlace != null
                && session.getPlacesRepository()
                        .deleted(selectedPlace)) {
            FMessage.info("Place '"
                    + selectedPlace.getName()
                    + "' deleted");
        } else {
            FMessage.error("Could not delete place");
        }
    }
    
    
    
    public void saveNewPlace() {
        if (newPlace != null
                && session.getPlacesRepository()
                        .persisted(newPlace)) {
            FMessage.info("Place '"
                    + newPlace.getName()
                    + "' Saved");
            loadPlaces();
        } else {
            FMessage.error("Could not save place");
        }
    }
    
    
    
    public void makeNewPlace() {
        newPlace = new Place();
        newPlace.setName("");
        newPlace.setArrivaldate(new Date());
        newPlace.setTripid(selectedTrip.getId());
    }
    
    
    
    public void selectTimeline(TimelineSelectEvent<String> e) {
        
        TimelineEvent<String> timelineEvent = e.getTimelineEvent();
        
        places.forEach(place -> {
            if (place.getName().equals(timelineEvent.getData())) {
                selectedPlace = place;
            }
        });
        
        tabIndex = 1;
        
    }
    
    
    
    public void editPlace() {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('placesDLG').show();");
    }
    
}
