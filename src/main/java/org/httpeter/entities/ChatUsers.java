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
@Table(name = "chat_users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChatUsers.findAll", query = "SELECT c FROM ChatUsers c")
    , @NamedQuery(name = "ChatUsers.findByUserName", query = "SELECT c FROM ChatUsers c WHERE c.userName = :userName")
    , @NamedQuery(name = "ChatUsers.findByIpAddress", query = "SELECT c FROM ChatUsers c WHERE c.ipAddress = :ipAddress")
    , @NamedQuery(name = "ChatUsers.findByLoginTime", query = "SELECT c FROM ChatUsers c WHERE c.loginTime = :loginTime")
    , @NamedQuery(name = "ChatUsers.findByChatUserId", query = "SELECT c FROM ChatUsers c WHERE c.chatUserId = :chatUserId")
    , @NamedQuery(name = "ChatUsers.findByStatus", query = "SELECT c FROM ChatUsers c WHERE c.status = :status")})
public class ChatUsers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(name = "user_name")
    private String userName;
    @Size(max = 50)
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "login_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginTime;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "chat_user_id")
    private Integer chatUserId;
    @Column(name = "status")
    private Integer status;

    public ChatUsers() {
    }

    public ChatUsers(Integer chatUserId) {
        this.chatUserId = chatUserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getChatUserId() {
        return chatUserId;
    }

    public void setChatUserId(Integer chatUserId) {
        this.chatUserId = chatUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chatUserId != null ? chatUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChatUsers)) {
            return false;
        }
        ChatUsers other = (ChatUsers) object;
        if ((this.chatUserId == null && other.chatUserId != null) || (this.chatUserId != null && !this.chatUserId.equals(other.chatUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.httpeter.entities.ChatUsers[ chatUserId=" + chatUserId + " ]";
    }
    
}
