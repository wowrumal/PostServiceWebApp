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
}
