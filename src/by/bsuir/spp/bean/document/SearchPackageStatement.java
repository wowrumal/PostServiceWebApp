package by.bsuir.spp.bean.document;

import by.bsuir.spp.bean.Passport;

import java.io.Serializable;
import java.lang.*;
import java.util.Date;

public class SearchPackageStatement implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String address;
    private String phoneNumber;
    private Passport passport;

    private String petitionContent;

    private Package postPackage;

    private Date currentDate;

    private String postManagerName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public String getPetitionContent() {
        return petitionContent;
    }

    public void setPetitionContent(String petitionContent) {
        this.petitionContent = petitionContent;
    }

    public Package getPostPackage() {
        return postPackage;
    }

    public void setPostPackage(Package postPackage) {
        this.postPackage = postPackage;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getPostManagerName() {
        return postManagerName;
    }

    public void setPostManagerName(String postManagerName) {
        this.postManagerName = postManagerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchPackageStatement that = (SearchPackageStatement) o;

        if (id != that.id) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (passport != null ? !passport.equals(that.passport) : that.passport != null) return false;
        if (petitionContent != null ? !petitionContent.equals(that.petitionContent) : that.petitionContent != null)
            return false;
        if (postPackage != null ? !postPackage.equals(that.postPackage) : that.postPackage != null) return false;
        if (currentDate != null ? !currentDate.equals(that.currentDate) : that.currentDate != null) return false;
        return !(postManagerName != null ? !postManagerName.equals(that.postManagerName) : that.postManagerName != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        result = 31 * result + (petitionContent != null ? petitionContent.hashCode() : 0);
        result = 31 * result + (postPackage != null ? postPackage.hashCode() : 0);
        result = 31 * result + (currentDate != null ? currentDate.hashCode() : 0);
        result = 31 * result + (postManagerName != null ? postManagerName.hashCode() : 0);
        return result;
    }
}
