package case1.nl.data.repository;

import case1.nl.entities.SysPage;
import case1.nl.util.FMessage;
import javax.persistence.Query;

/**
 *
 * @author peterhendriks
 */
public class SystemRepository extends DefaultRepository {
    
    public SystemRepository(String pu) {
        super(pu);
    }
    
}
