package case1.nl.util;

import case1.nl.entities.Place;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author peterhendriks
 */
public final class DateHelper {

    public static final LocalDate convertDateToLocalDate(java.util.Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }



    public static final String getIcalEvent(Place place) {

        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

        String templateIcalString = "BEGIN:VCALENDAR\n"
                + "VERSION:2.0\n"
                + "PRODID:-//case1.nl//NONSGML v1.0//EN\n"
                + "BEGIN:VEVENT\n"
                + "UID:" + new Date().getTime() + "@case1.nl\n"
                + "DTSTART;VALUE=DATE:" + DATE_FORMAT.format(place.getArrivaldate())
                + "\n"
                + "DTEND;VALUE=DATE:" + DATE_FORMAT.format(place.getDeparturedate())
                + "\n"
                + "SUMMARY:" + place.getName() + "\n"
                + "LOCATION:" + place.getAddress() + "\n"
                + "END:VEVENT\n"
                + "END:VCALENDAR";

        return templateIcalString;
    }

}
