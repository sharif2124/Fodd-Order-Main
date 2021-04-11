package com.ridoy.foodorder.ModelClasses;

public class Order {
    String orderName,orderPrice,orderNumber;
    int orderImage;

    public Order() {
    }

    public Order(String orderName, String orderPrice, String orderNumber, int orderImage) {
        this.orderName = orderName;
        this.orderPrice = orderPrice;
        this.orderNumber = orderNumber;
        this.orderImage = orderImage;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(int orderImage) {
        this.orderImage = orderImage;
    }
}
