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
    
    
    
    public SysPage getSysPageById(int sysPageId) {
        Query q = this.getEm().createQuery("select sp from SysPage sp "
                + "where sp.id = :sysPageId")
                .setParameter("sysPageId", sysPageId);
        SysPage result = null;
        try {
            result = (SysPage) q.getSingleResult();            
        } catch (Exception e) {
            FMessage.error(e.getLocalizedMessage());
        }
        
        return result;
    }
    
}
