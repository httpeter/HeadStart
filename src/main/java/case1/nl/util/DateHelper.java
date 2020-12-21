
package case1.nl.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author peterhendriks
 */
public final class DateHelper {
    
    public static final LocalDate convertDateToLocalDate(Date dateToConvert) {
    return dateToConvert.toInstant()
      .atZone(ZoneId.systemDefault())
      .toLocalDate();
}
    
}
