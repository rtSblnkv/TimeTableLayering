package TTL.entity;


import java.util.List;
import java.util.Map;

enum ORDER_TYPES{
    BREAKFAST,
    LUNCH,
    DINNER
}

public class Order {

    //order_id,date,time,order_type,branch_code,order_items,order_price,customer_lat,customer_lon,customerHasloyalty?,distance_to_customer_KM,delivery_fee
    private int orderId;
    private String date;
    private String time;
    private String orderType;
    private String branchCode;
    private Node location;
    private Boolean hasLoyality;
    private List<OrderItem> orderItems;
    private double order_price;
    private double distanceTo;
    private double delivery_fee;

    public Order(){}

    public Order(int orderId, String date, String time, String orderType, String branchCode, Node location, Boolean hasLoyality, List<OrderItem> orderItems, double order_price, double distanceTo, double delivery_fee) {
        this.orderId = orderId;
        this.date = date;
        this.time = time;
        this.orderType = orderType;
        this.branchCode = branchCode;
        this.location = location;
        this.hasLoyality = hasLoyality;
        this.orderItems = orderItems;
        this.order_price = order_price;
        this.distanceTo = distanceTo;
        this.delivery_fee = delivery_fee;
    }

    public Node getLocation() {
        return location;
    }

    public void setLocation(Node location) {
        this.location = location;
    }

    public Boolean getHasLoyality() {
        return hasLoyality;
    }

    public void setHasLoyality(Boolean hasLoyality) {
        this.hasLoyality = hasLoyality;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }

    public double getDistanceTo() {
        return distanceTo;
    }

    public void setDistanceTo(double distanceTo) {
        this.distanceTo = distanceTo;
    }

    public double getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(double delivery_fee) {
        this.delivery_fee = delivery_fee;
    }
}
