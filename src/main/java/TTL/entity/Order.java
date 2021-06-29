package TTL.entity;


import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import java.util.Date;
import java.util.List;


public class Order {

    @CsvBindByName(column="order_id")
    private String orderId;

    @CsvBindByName
    @CsvDate("YYYY-MM-DD")
    private Date date;

    @CsvBindByName
    private String time;

    @CsvBindByName(column="order_type")
    private String orderType;

    @CsvBindByName(column="branch_code")
    private String branchCode;

    @CsvBindByName(column="order_price")
    private double orderPrice;

    @CsvBindByName(column="customer_lat")
    private double latitude;

    @CsvBindByName(column="customer_lon")
    private double longtude;

    @CsvBindByName(column="customerHasloyalty?")
    private Boolean hasLoyality;

    //TODO: write converter for order lists

    //@CsvBindAndSplitByName(column="order_items",elementType= OrderItem.class)
    private List<OrderItem> orderItems;

    @CsvBindByName(column="order_items")
    private String ois;

    @CsvBindByName(column="distance_to_customer_KM")
    private double distanceTo;

    @CsvBindByName(column="delivery_fee")
    private double deliveryFee;

    public Order(){}

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public String getOis() {
        return ois;
    }

    public void setOis(String ois) {
        this.ois = ois;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtude() {
        return longtude;
    }

    public void setLongtude(double longtude) {
        this.longtude = longtude;
    }

    public Boolean getHasLoyality() {
        return hasLoyality;
    }

    public void setHasLoyality(Boolean hasLoyality) {
        this.hasLoyality = hasLoyality;
    }

    public List<OrderItem> getOrderItems() { return orderItems; }

    public void setOrderItems(List<OrderItem> orderItems){ this.orderItems = orderItems; }

    public double getDistanceTo() { return distanceTo; }

    public void setDistanceTo(double distanceTo) { this.distanceTo = distanceTo; }

    public double getDeliveryFee() { return deliveryFee; }

    public void setDeliveryFee(double deliveryFee) { this.deliveryFee = deliveryFee; }

    @Override
    public String toString() {
        if(orderItems != null)
        {
            StringBuilder ois = new StringBuilder("(");
            for (OrderItem oi : orderItems)
            {
                ois.append(oi.toString())  ;
            }
            ois.append(")");
            return "order id = " + orderId +
                    ", date = " + date +
                    ", time = " + time +
                    ", orderType = " + orderType  +
                    ", branchCode = " + branchCode +
                    ", orderPrice = " + orderPrice +
                    ", latitude = " + latitude +
                    ", longtude = " + longtude +
                    ", hasLoyality = " + hasLoyality +
                    ", orderItems = " + ois.toString() +
                    ", distanceTo = " + distanceTo +
                    ", deliveryFee = " + deliveryFee;
        }
        return "order id = " + orderId +
                ", date = " + date +
                ", time = " + time +
                ", orderType = " + orderType  +
                ", branchCode = " + branchCode +
                ", orderPrice = " + orderPrice +
                ", latitude = " + latitude +
                ", longtude = " + longtude +
                ", ois = " + ois +
                ", hasLoyality = " + hasLoyality +
                ", distanceTo = " + distanceTo +
                ", deliveryFee = " + deliveryFee;
    }
}
