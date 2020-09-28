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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author peterhendriks
 */
@Entity
@Table(name = "TRIP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trip.findAll", query = "SELECT t FROM Trip t"),
    @NamedQuery(name = "Trip.findById", query = "SELECT t FROM Trip t WHERE t.id = :id"),
    @NamedQuery(name = "Trip.findByUserid", query = "SELECT t FROM Trip t WHERE t.userid = :userid"),
    @NamedQuery(name = "Trip.findByName", query = "SELECT t FROM Trip t WHERE t.name = :name"),
    @NamedQuery(name = "Trip.findByDescription", query = "SELECT t FROM Trip t WHERE t.description = :description"),
    @NamedQuery(name = "Trip.findByNotes", query = "SELECT t FROM Trip t WHERE t.notes = :notes"),
    @NamedQuery(name = "Trip.findByHomeaddress", query = "SELECT t FROM Trip t WHERE t.homeaddress = :homeaddress"),
    @NamedQuery(name = "Trip.findByHomelat", query = "SELECT t FROM Trip t WHERE t.homelat = :homelat"),
    @NamedQuery(name = "Trip.findByHomelng", query = "SELECT t FROM Trip t WHERE t.homelng = :homelng")})
public class Trip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "USERID")
    private Integer userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NAME")
    private String name;
    @Size(max = 1024)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 1024)
    @Column(name = "NOTES")
    private String notes;
    @Size(max = 255)
    @Column(name = "HOMEADDRESS")
    private String homeaddress;
    @Size(max = 45)
    @Column(name = "HOMELAT")
    private String homelat;
    @Size(max = 45)
    @Column(name = "HOMELNG")
    private String homelng;



    public Trip() {
    }



    public Trip(Integer id) {
        this.id = id;
    }



    public Trip(Integer id, String name) {
        this.id = id;
        this.name = name;
    }



    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getUserid() {
        return userid;
    }



    public void setUserid(Integer userid) {
        this.userid = userid;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }



    public String getNotes() {
        return notes;
    }



    public void setNotes(String notes) {
        this.notes = notes;
    }



    public String getHomeaddress() {
        return homeaddress;
    }



    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }



    public String getHomelat() {
        return homelat;
    }



    public void setHomelat(String homelat) {
        this.homelat = homelat;
    }



    public String getHomelng() {
        return homelng;
    }



    public void setHomelng(String homelng) {
        this.homelng = homelng;
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
        if (!(object instanceof Trip)) {
            return false;
        }
        Trip other = (Trip) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "case1.nl.entities.Trip[ id=" + id + " ]";
    }
    
}
