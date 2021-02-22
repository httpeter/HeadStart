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
@Table(name = "USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPwdhash", query = "SELECT u FROM User u WHERE u.pwdhash = :pwdhash"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role"),
    @NamedQuery(name = "User.findByLanguage", query = "SELECT u FROM User u WHERE u.language = :language"),
    @NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "User.findByUsetoken", query = "SELECT u FROM User u WHERE u.usetoken = :usetoken"),
    @NamedQuery(name = "User.findByLandingpageid", query = "SELECT u FROM User u WHERE u.landingpageid = :landingpageid"),
    @NamedQuery(name = "User.findByVacationdaysinitial", query = "SELECT u FROM User u WHERE u.vacationdaysinitial = :vacationdaysinitial"),
    @NamedQuery(name = "User.findByVacationdaysleft", query = "SELECT u FROM User u WHERE u.vacationdaysleft = :vacationdaysleft")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 45)
    @Column(name = "PWDHASH")
    private String pwdhash;
    @Size(max = 45)
    @Column(name = "ROLE")
    private String role;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "LANGUAGE")
    private String language;
    @Size(max = 45)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Size(max = 45)
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "USETOKEN")
    private Integer usetoken;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LANDINGPAGEID")
    private int landingpageid;
    @Column(name = "VACATIONDAYSINITIAL")
    private Integer vacationdaysinitial;
    @Column(name = "VACATIONDAYSLEFT")
    private Integer vacationdaysleft;



    public User() {
    }



    public User(Integer id) {
        this.id = id;
    }



    public User(Integer id, String language, int landingpageid) {
        this.id = id;
        this.language = language;
        this.landingpageid = landingpageid;
    }



    public Integer getId() {
        return id;
    }



    public void setId(Integer id) {
        this.id = id;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public String getPwdhash() {
        return pwdhash;
    }



    public void setPwdhash(String pwdhash) {
        this.pwdhash = pwdhash;
    }



    public String getRole() {
        return role;
    }



    public void setRole(String role) {
        this.role = role;
    }



    public String getLanguage() {
        return language;
    }



    public void setLanguage(String language) {
        this.language = language;
    }



    public String getFirstname() {
        return firstname;
    }



    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }



    public String getLastname() {
        return lastname;
    }



    public void setLastname(String lastname) {
        this.lastname = lastname;
    }



    public Integer getUsetoken() {
        return usetoken;
    }



    public void setUsetoken(Integer usetoken) {
        this.usetoken = usetoken;
    }



    public int getLandingpageid() {
        return landingpageid;
    }



    public void setLandingpageid(int landingpageid) {
        this.landingpageid = landingpageid;
    }



    public Integer getVacationdaysinitial() {
        return vacationdaysinitial;
    }



    public void setVacationdaysinitial(Integer vacationdaysinitial) {
        this.vacationdaysinitial = vacationdaysinitial;
    }



    public Integer getVacationdaysleft() {
        return vacationdaysleft;
    }



    public void setVacationdaysleft(Integer vacationdaysleft) {
        this.vacationdaysleft = vacationdaysleft;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "case1.nl.entities.User[ id=" + id + " ]";
    }
    
}
