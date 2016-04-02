package by.bsuir.spp.bean.document;

import by.bsuir.spp.bean.User;

import java.io.Serializable;
import java.util.Date;

public class Package implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idPackage;
    private String type;
    private int barCode;
    private Date date;
    private String senderName;
    private User getterUser;
    private String address;
    private int postIndex;
    private int passportId;
    private String status;

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

    public int getBarCode() {
        return barCode;
    }

    public void setBarCode(int barCode) {
        this.barCode = barCode;
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
}

