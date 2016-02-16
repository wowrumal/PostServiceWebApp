package by.bsuir.spp.bean.document;

import by.bsuir.spp.bean.Passport;

import java.io.Serializable;

public class Advertisement implements Serializable {

    private static final long serialVersionUID = 1L;

    private int advertisementId;
    private Package postPackage;
    private int weight;
    private int cost;
    private Passport passport;
    private String addressForGetting;

    public int getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(int advertisementId) {
        this.advertisementId = advertisementId;
    }

    public Package getPostPackage() {
        return postPackage;
    }

    public void setPostPackage(Package postPackage) {
        this.postPackage = postPackage;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public String getAddressForGetting() {
        return addressForGetting;
    }

    public void setAddressForGetting(String addressForGetting) {
        this.addressForGetting = addressForGetting;
    }
}
