/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case1.nl.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author peterhendriks
 */
@Entity
@Table(name = "TRIPPARTICIPANT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tripparticipant.findAll", query = "SELECT t FROM Tripparticipant t"),
    @NamedQuery(name = "Tripparticipant.findById", query = "SELECT t FROM Tripparticipant t WHERE t.id = :id"),
    @NamedQuery(name = "Tripparticipant.findByUserid", query = "SELECT t FROM Tripparticipant t WHERE t.userid = :userid"),
    @NamedQuery(name = "Tripparticipant.findByParticipantid", query = "SELECT t FROM Tripparticipant t WHERE t.participantid = :participantid"),
    @NamedQuery(name = "Tripparticipant.findByCanedit", query = "SELECT t FROM Tripparticipant t WHERE t.canedit = :canedit"),
    @NamedQuery(name = "Tripparticipant.findByCanview", query = "SELECT t FROM Tripparticipant t WHERE t.canview = :canview"),
    @NamedQuery(name = "Tripparticipant.findByIspublic", query = "SELECT t FROM Tripparticipant t WHERE t.ispublic = :ispublic")})
public class Tripparticipant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERID")
    private int userid;
    @Column(name = "PARTICIPANTID")
    private Integer participantid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANEDIT")
    private boolean canedit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANVIEW")
    private boolean canview;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ISPUBLIC")
    private boolean ispublic;



    public Tripparticipant() {
    }



    public Tripparticipant(Integer id) {
        this.id = id;
    }



    public Tripparticipant(Integer id, int userid, boolean canedit, boolean canview, boolean ispublic) {
        this.id = id;
        this.userid = userid;
        this.canedit = canedit;
        this.canview = canview;
        this.ispublic = ispublic;
    }



    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }



    public int getUserid() {
        return userid;
    }



    public void setUserid(int userid) {
        this.userid = userid;
    }



    public Integer getParticipantid() {
        return participantid;
    }



    public void setParticipantid(Integer participantid) {
        this.participantid = participantid;
    }



    public boolean getCanedit() {
        return canedit;
    }



    public void setCanedit(boolean canedit) {
        this.canedit = canedit;
    }



    public boolean getCanview() {
        return canview;
    }



    public void setCanview(boolean canview) {
        this.canview = canview;
    }



    public boolean getIspublic() {
        return ispublic;
    }



    public void setIspublic(boolean ispublic) {
        this.ispublic = ispublic;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }



    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tripparticipant)) {
            return false;
        }
        Tripparticipant other = (Tripparticipant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "case1.nl.entities.Tripparticipant[ id=" + id + " ]";
    }
    
}
