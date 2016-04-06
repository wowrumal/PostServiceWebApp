package by.bsuir.spp.bean.document;

import java.io.Serializable;
import java.util.Date;

public class PrepaymentBookStatement implements Serializable {
    private static final long serialVersionUID = 1L;

    private int statementNumber;
    private String clientName;
    private int clientNumber;

    private int unpaidCost;

    private String organizationHeadName;
    private String bookkeeperName;

    private int passportId;

    public int getPassportId() {
        return passportId;
    }

    public void setPassportId(int passportId) {
        this.passportId = passportId;
    }

    private Date date;

    public int getStatementNumber() {
        return statementNumber;
    }

    public void setStatementNumber(int statementNumber) {
        this.statementNumber = statementNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(int clientNumber) {
        this.clientNumber = clientNumber;
    }

    public int getUnpaidCost() {
        return unpaidCost;
    }

    public void setUnpaidCost(int unpaidCost) {
        this.unpaidCost = unpaidCost;
    }

    public String getOrganizationHeadName() {
        return organizationHeadName;
    }

    public void setOrganizationHeadName(String organizationHeadName) {
        this.organizationHeadName = organizationHeadName;
    }

    public String getBookkeeperName() {
        return bookkeeperName;
    }

    public void setBookkeeperName(String bookkeeperName) {
        this.bookkeeperName = bookkeeperName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrepaymentBookStatement that = (PrepaymentBookStatement) o;

        if (statementNumber != that.statementNumber) return false;
        if (clientNumber != that.clientNumber) return false;
        if (unpaidCost != that.unpaidCost) return false;
        if (passportId != that.passportId) return false;
        if (clientName != null ? !clientName.equals(that.clientName) : that.clientName != null) return false;
        if (organizationHeadName != null ? !organizationHeadName.equals(that.organizationHeadName) : that.organizationHeadName != null)
            return false;
        if (bookkeeperName != null ? !bookkeeperName.equals(that.bookkeeperName) : that.bookkeeperName != null)
            return false;
        return !(date != null ? !date.equals(that.date) : that.date != null);

    }

    @Override
    public int hashCode() {
        int result = statementNumber;
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + clientNumber;
        result = 31 * result + unpaidCost;
        result = 31 * result + (organizationHeadName != null ? organizationHeadName.hashCode() : 0);
        result = 31 * result + (bookkeeperName != null ? bookkeeperName.hashCode() : 0);
        result = 31 * result + passportId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
