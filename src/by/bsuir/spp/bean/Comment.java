package by.bsuir.spp.bean;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {

    private static final long serialVersionUID = 1l;

    private int id;
    private String text;
    private Date date;
    private int packageId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }
}
