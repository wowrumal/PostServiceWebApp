package by.bsuir.spp.bean.document;

import by.bsuir.spp.bean.Passport;

import java.io.Serializable;

public class Advertisement implements Serializable {

    private static final long serialVersionUID = 1L;

    private Package postPackage;
    private int weight;
    private int cost;
    private Passport passport;
    private String addressForGetting;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Advertisement that = (Advertisement) o;

        if (weight != that.weight) return false;
        if (cost != that.cost) return false;
        if (postPackage != null ? !postPackage.equals(that.postPackage) : that.postPackage != null) return false;
        if (passport != null ? !passport.equals(that.passport) : that.passport != null) return false;
        return !(addressForGetting != null ? !addressForGetting.equals(that.addressForGetting) : that.addressForGetting != null);

    }

    @Override
    public int hashCode() {
        int result = postPackage != null ? postPackage.hashCode() : 0;
        result = 31 * result + weight;
        result = 31 * result + cost;
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        result = 31 * result + (addressForGetting != null ? addressForGetting.hashCode() : 0);
        return result;
    }
}
