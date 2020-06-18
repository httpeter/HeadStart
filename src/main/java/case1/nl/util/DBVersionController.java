package case1.nl.util;

import org.flywaydb.core.Flyway;

/**
 *
 * @author peterhendriks
 */
public class DBVersionController {

    public int migrateToLatest() {

        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/example?useLegacyDatetimeCode=false&serverTimezone=UTC", "case1", "")
                .load();
        flyway.baseline();

        return flyway.migrate();
    }

}
