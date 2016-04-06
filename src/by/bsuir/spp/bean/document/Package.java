package by.bsuir.spp.bean.document;

import by.bsuir.spp.bean.User;

import java.io.Serializable;
import java.util.Date;

public class Package implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idPackage;
    private String type;
    private Date date;
    private String senderName;
    private User getterUser;
    private String address;
    private int postIndex;
    private int passportId;
    private String status;
    private boolean deleted;
    private String trackNumber;

    public String getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPassportId() {
        return passportId;
    }

    public void setPassportId(int passportId) {
        this.passportId = passportId;
    }

    public int getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(int idPackage) {
        this.idPackage = idPackage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public User getGetterUser() {
        return getterUser;
    }

    public void setGetterUser(User getterUser) {
        this.getterUser = getterUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(int postIndex) {
        this.postIndex = postIndex;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Package aPackage = (Package) o;

        if (idPackage != aPackage.idPackage) return false;
        if (postIndex != aPackage.postIndex) return false;
        if (passportId != aPackage.passportId) return false;
        if (deleted != aPackage.deleted) return false;
        if (type != null ? !type.equals(aPackage.type) : aPackage.type != null) return false;
        if (date != null ? !date.equals(aPackage.date) : aPackage.date != null) return false;
        if (senderName != null ? !senderName.equals(aPackage.senderName) : aPackage.senderName != null) return false;
        if (getterUser != null ? !getterUser.equals(aPackage.getterUser) : aPackage.getterUser != null) return false;
        if (address != null ? !address.equals(aPackage.address) : aPackage.address != null) return false;
        if (status != null ? !status.equals(aPackage.status) : aPackage.status != null) return false;
        return !(trackNumber != null ? !trackNumber.equals(aPackage.trackNumber) : aPackage.trackNumber != null);

    }

    @Override
    public int hashCode() {
        int result = idPackage;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (senderName != null ? senderName.hashCode() : 0);
        result = 31 * result + (getterUser != null ? getterUser.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + postIndex;
        result = 31 * result + passportId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (deleted ? 1 : 0);
        result = 31 * result + (trackNumber != null ? trackNumber.hashCode() : 0);
        return result;
    }
}

