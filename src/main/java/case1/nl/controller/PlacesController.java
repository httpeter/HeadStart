package case1.nl.controller;

import case1.nl.entities.Place;
import case1.nl.entities.Trip;
import case1.nl.util.DateHelper;
import case1.nl.util.FMessage;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.Instant;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
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

    private List<String> gallery;

    private Trip selectedTrip = new Trip();

    private Trip newTrip = new Trip();

    private int selectedTripDuration;

    private List<Place> places;

    private Place selectedPlace = new Place();

    private Place newPlace = new Place();

    private MapModel mapModel,
            mapModelDetail;

    private int totalPrice,
            stillToPay;

    private String selectedTripIcal;



    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public int getStillToPay() {
        return stillToPay;
    }



    public void setStillToPay(int stillToPay) {
        this.stillToPay = stillToPay;
    }



    public int getTotalPrice() {
        return totalPrice;
    }



    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }



    public Trip getNewTrip() {
        return newTrip;
    }



    public void setNewTrip(Trip newTrip) {
        this.newTrip = newTrip;
    }



    public List<String> getGallery() {
        return gallery;
    }



    public void setGallery(List<String> gallery) {
        this.gallery = gallery;
    }



    public MapModel getMapModelDetail() {
        return mapModelDetail;
    }



    public void setMapModelDetail(MapModel mapModelDetail) {
        this.mapModelDetail = mapModelDetail;
    }



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
        if (id != 0) {
            this.selectedTrip = trips.stream()
                    .filter(trip -> trip.getId() == id)
                    .findFirst()
                    .get();

            totalPrice = 0;
            stillToPay = 0;
        }
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
                .findAll(Trip.class);

        selectedTrip = new Trip();
        selectedTrip.setId(0);

    }



    public void loadPlaces() {

        newPlace = new Place();
        totalPrice = 0;
        stillToPay = 0;
        selectedTripDuration = 0;

        if (selectedTrip.getId() != 0) {
            places = session.getPlacesRepository()
                    .findByNamedQueryName("Place.findByTripid", selectedTrip.getId());

            timelineModel = new TimelineModel();

            mapModel = new DefaultMapModel();

            gallery = new ArrayList(places.size());

            //Adding home address to map                       
            try {
                LatLng homeCoord = new LatLng(Double.parseDouble(selectedTrip.getHomelat()),
                        Double.parseDouble(selectedTrip.getHomelng()));
                mapModel.addOverlay(new Marker(homeCoord, selectedTrip.getHomeaddress()));
            } catch (Exception e) {
                FMessage.warn("Could not plot home address on map \n\n"
                        + e.getLocalizedMessage());
            }

            places.forEach((place) -> {

                if (place != null
                        && place.getArrivaldate() != null
                        && place.getDeparturedate() != null) {

                    //Filling the timeline model
                    timelineModel.add(TimelineEvent.<Place>builder()
                            .data(place)
                            .startDate(Instant.ofEpochMilli(place.getArrivaldate()
                                    .getTime())
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate())
                            .endDate(Instant.ofEpochMilli(place.getDeparturedate()
                                    .getTime())
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate())
                            .build());

                    //Filling the ical
                    //selectedTripIcal+=
                } else {
                    FMessage.fatal("A PLACE COULD NOT BE LOADED!");
                    return;
                }

                //Filling the map model
                if (place.getLat() != null && place.getLng() != null) {
                    try {
                        LatLng coord = new LatLng(Double.parseDouble(place.getLat()),
                                Double.parseDouble(place.getLng()));

                        mapModel.addOverlay(new Marker(coord, place.getName()));
                    } catch (NumberFormatException e) {
                        FMessage.warn("Could not parse coordinates to the map "
                                + e.getMessage());
                    }
                }

                //Filling the gallery images
                gallery.add(place.getImgurls());
                try {
                    //Filling the total price and still to pay 
                    String price = place.getPrice();
                    if (price != null) {
                        totalPrice += Integer.parseInt(price);
                        if (!place.getPayed()) {
                            stillToPay += Integer.parseInt(price);
                        }
                    } else {
                        FMessage.warn("Prices cannot be calculated correctly, "
                                + "please enter prices");
                    }

                    //Calculating total duration                    
                    selectedTripDuration += Period.between(DateHelper.convertDateToLocalDate(place.getArrivaldate()),
                            DateHelper.convertDateToLocalDate(place.getDeparturedate()))
                            .getDays();

                } catch (Exception e) {
                    FMessage.warn(e.getMessage());
                }

            });

        }
    }



    public void mapSelect(PointSelectEvent event) {
        FMessage.info("Selected: " + event.getLatLng());
    }



    public void saveSelectedTrip() {

        if (session.getPlacesRepository()
                .merged(selectedTrip)) {
            FMessage.info("Trip Saved");
        } else {
            FMessage.error("Could not save trip");
        }

    }



    public int getSelectedPlaceTotalDays(Date arrivalDate, Date departureDate) {
        int days = 0;
        if (arrivalDate != null && departureDate != null) {
            days = Period.between(DateHelper.convertDateToLocalDate(arrivalDate),
                    DateHelper.convertDateToLocalDate(departureDate))
                    .getDays();
        }
        return days;
    }



    public void saveSelectedPlace() {

        if (selectedPlace != null) {
            try {
                selectedPlace.setPayedbyuserid(session.getCurrentUser().getId());
                session.getPlacesRepository().merged(selectedPlace);
                FMessage.info("Place '"
                        + selectedPlace.getName()
                        + "' Saved");

                loadPlaces();
            } catch (Exception e) {
                FMessage.error(e.getLocalizedMessage());
            }

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
            this.loadPlaces();
        } else {
            FMessage.error("Could not delete place");
        }
    }



    public void deleteSelectedTrip() {
        if (selectedTrip != null
                && session.getPlacesRepository()
                        .deleted(selectedTrip)) {
            FMessage.info("Trip '"
                    + selectedTrip.getName()
                    + "' deleted");
            try {
                this.loadTrips();
            } catch (Exception ex) {
                FMessage.error(ex.getMessage());
            }
        } else {
            FMessage.error("Could not delete trip");
        }
    }



    public void saveNewPlace() {

        if (newPlace != null) {
            try {
                newPlace.setPayedbyuserid(session.getCurrentUser().getId());
                session.getPlacesRepository().persisted(newPlace);
                FMessage.info("Place '"
                        + newPlace.getName()
                        + "' Saved");
                loadPlaces();
            } catch (Exception e) {
                FMessage.error(e.getLocalizedMessage());
            }
        } else {
            FMessage.error("Could not save place");
        }
    }



    public void makeNewPlace() {
        newPlace = new Place();
        newPlace.setName("");
        newPlace.setBooked(false);
        newPlace.setPayed(false);
        newPlace.setArrivaldate(new Date());
        newPlace.setTripid(selectedTrip.getId());
        newPlace.setLat("52.0841037");
        newPlace.setLng("4.9424081");

        PrimeFaces.current().executeScript("PF('addPlaceDLG').show();");
    }



    public void makeNewTrip() {
        newTrip = new Trip();
        newTrip.setUserid(session.getCurrentUser().getId());
        newTrip.setHomeaddress("Gerrit Doustraat 43\n2311XM Leiden\nNederland");
        newTrip.setHomelat("52.1529984");
        newTrip.setHomelng("4.4842176");
    }



    public void saveNewTrip() {
        if (newTrip != null
                && session.getPlacesRepository()
                        .persisted(newTrip)) {
            FMessage.info("Trip '"
                    + newTrip.getName()
                    + "' Saved");

            try {
                loadTrips();
            } catch (Exception ex) {
                Logger.getLogger(PlacesController.class.getName())
                        .log(Level.SEVERE, null, ex);
                FMessage.error(ex.getMessage());
            }

            loadPlaces();
        } else {
            FMessage.error("Could not save trip");
        }
    }



    public void timelineSelect(TimelineSelectEvent<Place> e) {

        selectedPlace = (Place) e.getTimelineEvent().getData();

        editPlace();

    }



    public void editPlace() {

        mapModelDetail = new DefaultMapModel();

        LatLng coord = new LatLng(Double.parseDouble(selectedPlace.getLat()),
                Double.parseDouble(selectedPlace.getLng()));

        mapModelDetail.addOverlay(new Marker(coord, selectedPlace.getName()));

        PrimeFaces.current().executeScript("PF('placeEditDLG').show();");
    }



    public void downloadIcal(Place place) {
        try {

            if (place == null) {
                place = selectedPlace;
            }

            // Prepare.
            byte[] icalData = DateHelper.getIcalEvent(place)
                    .getBytes();

            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

            // Initialize response.
            response.reset();
            response.setContentType("text/html");
            response.setHeader("Content-disposition", "attachment; filename=\""
                    + place.getName()
                    + ".ics\"");

            // Write file to response.
            OutputStream output = response.getOutputStream();
            output.write(icalData);
            output.close();

            // Inform JSF to not take the response in hands.
            facesContext.responseComplete();

        } catch (Exception ex) {
            FMessage.error(ex.getLocalizedMessage());
            Logger.getLogger(PlacesController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

}
