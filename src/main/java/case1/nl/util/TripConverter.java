package case1.nl.util;

import case1.nl.controller.SessionController;
import case1.nl.data.repository.PlacesRepository;
import case1.nl.entities.Trip;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("tripConverter")
public class TripConverter implements Converter {

    @ManagedProperty(value = "#{sessionController}")
    private SessionController session;
    
    private Trip trip;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        trip = session.getPlacesRepository().getTrip(value);
        System.out.println("TRIP=====> " + trip);
        if (trip != null) {
            return trip;
        } else {
            throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to Trip", value)));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        return String.valueOf(((Trip) value).getName());
    }

}
