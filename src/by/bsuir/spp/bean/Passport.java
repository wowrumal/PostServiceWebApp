package by.bsuir.spp.bean;

import java.io.Serializable;
import java.util.Date;


public class Passport implements Serializable {

    private static final long serialVersionUID = 1L;

    private int passportId;
    private String passportNumber;
    private String address;
    private String issuingInstitution;
    private Date issueDate;

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIssuingInstitution() {
        return issuingInstitution;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setIssuingInstitution(String issuingInstitution) {
        this.issuingInstitution = issuingInstitution;
    }

    public int getPassportId() {
        return passportId;
    }

    public void setPassportId(int passportId) {
        this.passportId = passportId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Passport passport = (Passport) o;

        if (passportId != passport.passportId) return false;
        if (passportNumber != null ? !passportNumber.equals(passport.passportNumber) : passport.passportNumber != null)
            return false;
        if (address != null ? !address.equals(passport.address) : passport.address != null) return false;
        if (issuingInstitution != null ? !issuingInstitution.equals(passport.issuingInstitution) : passport.issuingInstitution != null)
            return false;
        return !(issueDate != null ? !issueDate.equals(passport.issueDate) : passport.issueDate != null);

    }

    @Override
    public int hashCode() {
        int result = passportId;
        result = 31 * result + (passportNumber != null ? passportNumber.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (issuingInstitution != null ? issuingInstitution.hashCode() : 0);
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        return result;
    }
}
