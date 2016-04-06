package by.bsuir.spp.bean.document;

import java.io.Serializable;
import java.util.Date;

public class Receipt implements Serializable {

    private static final long serialVersionUID = 1L;

    private int receiptId;
    private String clientName;
    private String serviceName;
    private String paymentData;
    private int cost;
    private Date date;
    private int userId;


    public int getReceiptId() {
        return receiptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receipt receipt = (Receipt) o;

        if (receiptId != receipt.receiptId) return false;
        if (cost != receipt.cost) return false;
        if (userId != receipt.userId) return false;
        if (clientName != null ? !clientName.equals(receipt.clientName) : receipt.clientName != null) return false;
        if (serviceName != null ? !serviceName.equals(receipt.serviceName) : receipt.serviceName != null) return false;
        if (paymentData != null ? !paymentData.equals(receipt.paymentData) : receipt.paymentData != null) return false;
        return !(date != null ? !date.equals(receipt.date) : receipt.date != null);

    }

    @Override
    public int hashCode() {
        int result = receiptId;
        result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (paymentData != null ? paymentData.hashCode() : 0);
        result = 31 * result + cost;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + userId;
        return result;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(String paymentData) {
        this.paymentData = paymentData;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
