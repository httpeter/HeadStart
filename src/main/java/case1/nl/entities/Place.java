/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package case1.nl.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author peterhendriks
 */
@Entity
@Table(name = "PLACE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Place.findAll", query = "SELECT p FROM Place p"),
    @NamedQuery(name = "Place.findById", query = "SELECT p FROM Place p WHERE p.id = :id"),
    @NamedQuery(name = "Place.findByName", query = "SELECT p FROM Place p WHERE p.name = :name"),
    @NamedQuery(name = "Place.findByRating", query = "SELECT p FROM Place p WHERE p.rating = :rating"),
    @NamedQuery(name = "Place.findByDescription", query = "SELECT p FROM Place p WHERE p.description = :description"),
    @NamedQuery(name = "Place.findByImgurls", query = "SELECT p FROM Place p WHERE p.imgurls = :imgurls")})
public class Place implements Serializable {

    @Column(name = "ARRIVALDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivaldate;
    @Column(name = "DEPARTUREDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departuredate;
    @Size(max = 255)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 45)
    @Column(name = "COORDINATES")
    private String coordinates;
    @Size(max = 45)
    @Column(name = "COUNTRY")
    private String country;

    @Basic(optional = false)
    @NotNull
    @Column(name = "TRIPID")
    private int tripid;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NAME")
    private String name;
    @Column(name = "RATING")
    private Integer rating;
    @Size(max = 512)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 1024)
    @Column(name = "IMGURLS")
    private String imgurls;

    public Place() {
    }

    public Place(Integer id) {
        this.id = id;
    }

    public Place(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgurls() {
        return imgurls;
    }

    public void setImgurls(String imgurls) {
        this.imgurls = imgurls;
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
        if (!(object instanceof Place)) {
            return false;
        }
        Place other = (Place) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "case1.nl.entities.Place[ id=" + id + " ]";
    }

    public int getTripid() {
        return tripid;
    }

    public void setTripid(int tripid) {
        this.tripid = tripid;
    }

    public Date getArrivaldate() {
        return arrivaldate;
    }

    public void setArrivaldate(Date arrivaldate) {
        this.arrivaldate = arrivaldate;
    }

    public Date getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(Date departuredate) {
        this.departuredate = departuredate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
}
