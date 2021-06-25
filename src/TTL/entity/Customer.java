package TTL.entity;

import java.io.Serializable;

public class Customer implements Serializable {

    private Node customerLocation;
    private Boolean hasLoyality;

    public Customer(){}

    public Node getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(Node customerLocation) {
        this.customerLocation = customerLocation;
    }

    public Boolean getHasLoyality() {
        return hasLoyality;
    }

    public void setHasLoyality(Boolean hasLoyality) {
        this.hasLoyality = hasLoyality;
    }



}
