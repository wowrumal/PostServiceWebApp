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
}
