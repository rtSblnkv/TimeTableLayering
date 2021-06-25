package TTL.entity;

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
    private int orderType;
    private  String branchCode;
    private Map<String,Integer> orderItems;
    private double order_price;
    private double distanceTo;
    private double delivery_fee;

    public Order(){}

    public Order(int orderId, String date, String time, int orderType, String branchCode, Map<String, Integer> orderItems, double order_price, double distanceTo, double delivery_fee) {
        this.orderId = orderId;
        this.date = date;
        this.time = time;
        this.orderType = orderType;
        this.branchCode = branchCode;
        this.orderItems = orderItems;
        this.order_price = order_price;
        this.distanceTo = distanceTo;
        this.delivery_fee = delivery_fee;
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

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public Map<String, Integer> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Map<String, Integer> orderItems) {
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
