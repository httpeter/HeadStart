package case1.nl.util;

import java.io.Serializable;

/**
 *
 * @author peterhendriks
 */
public final class Validator implements Serializable {

    public static boolean isEmailAddress(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        boolean isValid = email.matches(regex);
        if (!isValid) {
            FMessage.warn("'"
                    + email
                    + "' is not a valid email address!");
        }
        return isValid;
    }

}
