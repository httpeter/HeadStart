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
    @NamedQuery(name = "Place.findByTripid", query = "SELECT p FROM Place p WHERE p.tripid = :tripid"),
    @NamedQuery(name = "Place.findByName", query = "SELECT p FROM Place p WHERE p.name = :name"),
    @NamedQuery(name = "Place.findByPayed", query = "SELECT p FROM Place p WHERE p.payed = :payed"),
    @NamedQuery(name = "Place.findByBooked", query = "SELECT p FROM Place p WHERE p.booked = :booked"),
    @NamedQuery(name = "Place.findByRating", query = "SELECT p FROM Place p WHERE p.rating = :rating"),
    @NamedQuery(name = "Place.findByDescription", query = "SELECT p FROM Place p WHERE p.description = :description"),
    @NamedQuery(name = "Place.findByImgurls", query = "SELECT p FROM Place p WHERE p.imgurls = :imgurls"),
    @NamedQuery(name = "Place.findByDeparturedate", query = "SELECT p FROM Place p WHERE p.departuredate = :departuredate"),
    @NamedQuery(name = "Place.findByArrivaldate", query = "SELECT p FROM Place p WHERE p.arrivaldate = :arrivaldate"),
    @NamedQuery(name = "Place.findByAddress", query = "SELECT p FROM Place p WHERE p.address = :address"),
    @NamedQuery(name = "Place.findByLat", query = "SELECT p FROM Place p WHERE p.lat = :lat"),
    @NamedQuery(name = "Place.findByLng", query = "SELECT p FROM Place p WHERE p.lng = :lng"),
    @NamedQuery(name = "Place.findByCountry", query = "SELECT p FROM Place p WHERE p.country = :country"),
    @NamedQuery(name = "Place.findByFreecancellationdate", query = "SELECT p FROM Place p WHERE p.freecancellationdate = :freecancellationdate"),
    @NamedQuery(name = "Place.findByOptional", query = "SELECT p FROM Place p WHERE p.optional = :optional"),
    @NamedQuery(name = "Place.findByPayedbyuserid", query = "SELECT p FROM Place p WHERE p.payedbyuserid = :payedbyuserid"),
    @NamedQuery(name = "Place.findByUrls", query = "SELECT p FROM Place p WHERE p.urls = :urls"),
    @NamedQuery(name = "Place.findByPrice", query = "SELECT p FROM Place p WHERE p.price = :price")})
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRIPID")
    private int tripid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PAYED")
    private boolean payed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BOOKED")
    private boolean booked;
    @Column(name = "RATING")
    private Integer rating;
    @Size(max = 500)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 500)
    @Column(name = "IMGURLS")
    private String imgurls;
    @Column(name = "DEPARTUREDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date departuredate;
    @Column(name = "ARRIVALDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date arrivaldate;
    @Size(max = 255)
    @Column(name = "ADDRESS")
    private String address;
    @Size(max = 45)
    @Column(name = "LAT")
    private String lat;
    @Size(max = 45)
    @Column(name = "LNG")
    private String lng;
    @Size(max = 45)
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "FREECANCELLATIONDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date freecancellationdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OPTIONAL")
    private boolean optional;
    @Column(name = "PAYEDBYUSERID")
    private Integer payedbyuserid;
    @Size(max = 500)
    @Column(name = "URLS")
    private String urls;
    @Size(max = 45)
    @Column(name = "PRICE")
    private String price;



    public Place() {
    }



    public Place(Integer id) {
        this.id = id;
    }



    public Place(Integer id, int tripid, String name, boolean payed, boolean booked, boolean optional) {
        this.id = id;
        this.tripid = tripid;
        this.name = name;
        this.payed = payed;
        this.booked = booked;
        this.optional = optional;
    }



    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }



    public int getTripid() {
        return tripid;
    }



    public void setTripid(int tripid) {
        this.tripid = tripid;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public boolean getPayed() {
        return payed;
    }



    public void setPayed(boolean payed) {
        this.payed = payed;
    }



    public boolean getBooked() {
        return booked;
    }



    public void setBooked(boolean booked) {
        this.booked = booked;
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



    public Date getDeparturedate() {
        return departuredate;
    }



    public void setDeparturedate(Date departuredate) {
        this.departuredate = departuredate;
    }



    public Date getArrivaldate() {
        return arrivaldate;
    }



    public void setArrivaldate(Date arrivaldate) {
        this.arrivaldate = arrivaldate;
    }



    public String getAddress() {
        return address;
    }



    public void setAddress(String address) {
        this.address = address;
    }



    public String getLat() {
        return lat;
    }



    public void setLat(String lat) {
        this.lat = lat;
    }



    public String getLng() {
        return lng;
    }



    public void setLng(String lng) {
        this.lng = lng;
    }



    public String getCountry() {
        return country;
    }



    public void setCountry(String country) {
        this.country = country;
    }



    public Date getFreecancellationdate() {
        return freecancellationdate;
    }



    public void setFreecancellationdate(Date freecancellationdate) {
        this.freecancellationdate = freecancellationdate;
    }



    public boolean getOptional() {
        return optional;
    }



    public void setOptional(boolean optional) {
        this.optional = optional;
    }



    public Integer getPayedbyuserid() {
        return payedbyuserid;
    }



    public void setPayedbyuserid(Integer payedbyuserid) {
        this.payedbyuserid = payedbyuserid;
    }



    public String getUrls() {
        return urls;
    }



    public void setUrls(String urls) {
        this.urls = urls;
    }



    public String getPrice() {
        return price;
    }



    public void setPrice(String price) {
        this.price = price;
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
    
}
