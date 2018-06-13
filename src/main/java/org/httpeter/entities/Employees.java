/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.httpeter.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "employees")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employees e")
    , @NamedQuery(name = "Employees.findByEmployeeid", query = "SELECT e FROM Employees e WHERE e.employeeid = :employeeid")
    , @NamedQuery(name = "Employees.findByLastname", query = "SELECT e FROM Employees e WHERE e.lastname = :lastname")
    , @NamedQuery(name = "Employees.findByFirstname", query = "SELECT e FROM Employees e WHERE e.firstname = :firstname")
    , @NamedQuery(name = "Employees.findByTitle", query = "SELECT e FROM Employees e WHERE e.title = :title")
    , @NamedQuery(name = "Employees.findByTitleofcourtesy", query = "SELECT e FROM Employees e WHERE e.titleofcourtesy = :titleofcourtesy")
    , @NamedQuery(name = "Employees.findByBirthdate", query = "SELECT e FROM Employees e WHERE e.birthdate = :birthdate")
    , @NamedQuery(name = "Employees.findByHiredate", query = "SELECT e FROM Employees e WHERE e.hiredate = :hiredate")
    , @NamedQuery(name = "Employees.findByAddress", query = "SELECT e FROM Employees e WHERE e.address = :address")
    , @NamedQuery(name = "Employees.findByCity", query = "SELECT e FROM Employees e WHERE e.city = :city")
    , @NamedQuery(name = "Employees.findByRegion", query = "SELECT e FROM Employees e WHERE e.region = :region")
    , @NamedQuery(name = "Employees.findByPostalcode", query = "SELECT e FROM Employees e WHERE e.postalcode = :postalcode")
    , @NamedQuery(name = "Employees.findByCountry", query = "SELECT e FROM Employees e WHERE e.country = :country")
    , @NamedQuery(name = "Employees.findByHomephone", query = "SELECT e FROM Employees e WHERE e.homephone = :homephone")
    , @NamedQuery(name = "Employees.findByExtension", query = "SELECT e FROM Employees e WHERE e.extension = :extension")
    , @NamedQuery(name = "Employees.findByNotes", query = "SELECT e FROM Employees e WHERE e.notes = :notes")
    , @NamedQuery(name = "Employees.findByReportsto", query = "SELECT e FROM Employees e WHERE e.reportsto = :reportsto")
    , @NamedQuery(name = "Employees.findByPhotopath", query = "SELECT e FROM Employees e WHERE e.photopath = :photopath")})
public class Employees implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "employeeid")
    private Integer employeeid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 30)
    @Column(name = "title")
    private String title;
    @Size(max = 25)
    @Column(name = "titleofcourtesy")
    private String titleofcourtesy;
    @Column(name = "birthdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthdate;
    @Column(name = "hiredate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hiredate;
    @Size(max = 60)
    @Column(name = "address")
    private String address;
    @Size(max = 15)
    @Column(name = "city")
    private String city;
    @Size(max = 15)
    @Column(name = "region")
    private String region;
    @Size(max = 10)
    @Column(name = "postalcode")
    private String postalcode;
    @Size(max = 15)
    @Column(name = "country")
    private String country;
    @Size(max = 24)
    @Column(name = "homephone")
    private String homephone;
    @Size(max = 4)
    @Column(name = "extension")
    private String extension;
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @Size(max = 4000)
    @Column(name = "notes")
    private String notes;
    @Column(name = "reportsto")
    private Integer reportsto;
    @Size(max = 255)
    @Column(name = "photopath")
    private String photopath;

    public Employees() {
    }

    public Employees(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public Employees(Integer employeeid, String lastname, String firstname) {
        this.employeeid = employeeid;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleofcourtesy() {
        return titleofcourtesy;
    }

    public void setTitleofcourtesy(String titleofcourtesy) {
        this.titleofcourtesy = titleofcourtesy;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getReportsto() {
        return reportsto;
    }

    public void setReportsto(Integer reportsto) {
        this.reportsto = reportsto;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeid != null ? employeeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employees)) {
            return false;
        }
        Employees other = (Employees) object;
        if ((this.employeeid == null && other.employeeid != null) || (this.employeeid != null && !this.employeeid.equals(other.employeeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.httpeter.entities.Employees[ employeeid=" + employeeid + " ]";
    }
    
}
